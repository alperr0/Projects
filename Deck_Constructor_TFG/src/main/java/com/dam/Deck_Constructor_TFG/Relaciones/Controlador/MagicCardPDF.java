package com.dam.Deck_Constructor_TFG.Relaciones.Controlador;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class MagicCardPDF {
	public static void crearPDF (ArrayList<String[]> cardNames) {
        ArrayList<String[]> cardInfo = new ArrayList<String[]>();
        MtgApiRequest api = MtgApiRequest.getInstance();
        for (String[] c : cardNames) {
            String urlImagen = api.getImagenCartaPng(c[0]);
            String nCopias= c[1];
            cardInfo.add(new String[] {urlImagen, nCopias});
        }

        float anchoCartaPulgadas = 2.375f * 72;
        float alturaCartaPulgadas = 3.325f * 72;

        Rectangle pageSize = PageSize.A4;

        float marginLeftRight = (pageSize.getWidth() - (3 * anchoCartaPulgadas)) / 2;
        float marginTopBottom = (pageSize.getHeight() - (3 * alturaCartaPulgadas)) / 2;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF");
        
        FileNameExtensionFilter pdfFilter = new FileNameExtensionFilter("Archivos PDF", "pdf");
        fileChooser.addChoosableFileFilter(pdfFilter);
        fileChooser.setFileFilter(pdfFilter); 
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();

            if (!fichero.getAbsolutePath().toLowerCase().endsWith(".pdf")) {
                fichero = new File(fichero.getAbsolutePath() + ".pdf");
            }
            try {

                Document document = new Document(pageSize);
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fichero));
                document.open();


                int filas = 3;
                int columnas = 3;

                int i = 0;

                while (i < cardInfo.size()) {
 
                    document.newPage();


                    i = addCardsToPage(document, writer, cardInfo, i, filas, columnas, anchoCartaPulgadas, alturaCartaPulgadas, marginLeftRight, marginTopBottom);
                }


                document.close();
                System.out.println("PDF creado con éxito en: " + fichero.getAbsolutePath());


                Desktop.getDesktop().open(fichero);
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        }
    }
	private static int addCardsToPage(Document document, PdfWriter writer, ArrayList<String[]> cardInfo, int startingIndex, int cardsPerRow, int cardsPerColumn, float cardWidthPoints, float cardHeightPoints, float marginLeftRight, float marginTopBottom) throws DocumentException, IOException {
        int cardCounter = startingIndex;
        int currentRow = 0;
        int currentCol = 0;

        while (currentRow < cardsPerColumn && cardCounter < cardInfo.size()) {
            String imageUrl = cardInfo.get(cardCounter)[0];
            int nCopias = Integer.parseInt(cardInfo.get(cardCounter)[1]);

            for (int copy = 0; copy < nCopias; copy++) {

                BufferedImage bufferedImage = ImageIO.read(new URL(imageUrl));
                Image image = Image.getInstance(bufferedImage, null);


                image.scaleAbsolute(cardWidthPoints, cardHeightPoints);


                float x = marginLeftRight + currentCol * cardWidthPoints;
                float y = marginTopBottom + (2 * cardHeightPoints) - (currentRow * cardHeightPoints);
                image.setAbsolutePosition(x, y);


                document.add(image);


                currentCol++;
                if (currentCol == cardsPerRow) {
                    currentCol = 0;
                    currentRow++;
                    if (currentRow == cardsPerColumn) {

                        cardCounter++;
                        return cardCounter;
                    }
                }
            }
            cardCounter++;
        }
        return cardCounter;
    }
 }

