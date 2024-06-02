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
	public static void crearPDF (ArrayList<String> cardNames) {
		 // URL de las imágenes de las cartas
        ArrayList<String> cardImages = new ArrayList<String>();
        MtgApiRequest api = MtgApiRequest.getInstance();
        for (String c : cardNames) {
            String urlImagen = api.getImagenCartaPng(c);
            cardImages.add(urlImagen);
        }

     // Dimensiones de una carta Magic en puntos (1 pulgada = 72 puntos)
        float anchoCartaPulgadas = 2.375f * 72;
        float alturaCartaPulgadas = 3.325f * 72;

        // Tamaño de una página DIN A4 en puntos
        Rectangle pageSize = PageSize.A4;

        // Margen adicional para ajustar el tamaño de las cartas
        float marginLeftRight = (pageSize.getWidth() - (3 * anchoCartaPulgadas)) / 2;
        float marginTopBottom = (pageSize.getHeight() - (3 * alturaCartaPulgadas)) / 2;

        // Selección de la ruta del archivo con JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF");
     // Añadir filtro para archivos PDF
        FileNameExtensionFilter pdfFilter = new FileNameExtensionFilter("Archivos PDF", "pdf");
        fileChooser.addChoosableFileFilter(pdfFilter);
        fileChooser.setFileFilter(pdfFilter); 
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            // Agregar la extensión .pdf al nombre del archivo si no está presente
            if (!fichero.getAbsolutePath().toLowerCase().endsWith(".pdf")) {
                fichero = new File(fichero.getAbsolutePath() + ".pdf");
            }
            try {
                // Crear un PdfWriter y un Document
                Document document = new Document(pageSize);
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fichero));
                document.open();

                // Número de cartas por fila y columna
                int filas = 3;
                int columnas = 3;

                // Contador de cartas
                int i = 0;

                while (i < cardImages.size()) {
                    // Agregar una nueva página
                    document.newPage();

                    // Reiniciar el contador de cartas para la nueva página
                    i = addCardsToPage(document, writer, cardImages, i, filas, columnas, anchoCartaPulgadas, alturaCartaPulgadas, marginLeftRight, marginTopBottom);
                }

                // Cerrar el documento
                document.close();
                System.out.println("PDF creado con éxito en: " + fichero.getAbsolutePath());

                // Abrir el archivo en el navegador
                Desktop.getDesktop().open(fichero);
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        }
    }
	private static int addCardsToPage(Document document, PdfWriter writer, ArrayList<String> cardImages, int startingIndex, int cardsPerRow, int cardsPerColumn, float cardWidthPoints, float cardHeightPoints, float marginLeftRight, float marginTopBottom) throws DocumentException, IOException {
	    int cardCounter = startingIndex;

	    for (int row = 0; row < cardsPerColumn && cardCounter < cardImages.size(); row++) {
	        for (int col = 0; col < cardsPerRow && cardCounter < cardImages.size(); col++) {
	            String imageUrl = cardImages.get(cardCounter);

	            // Cargar imagen desde la URL
	            BufferedImage bufferedImage = ImageIO.read(new URL(imageUrl));
	            Image image = Image.getInstance(bufferedImage, null);

	            // Escalar imagen al tamaño real de la carta
	            image.scaleAbsolute(cardWidthPoints, cardHeightPoints);

	            // Coordenadas absolutas para cada carta
	            float x = marginLeftRight + col * cardWidthPoints;
	            float y = marginTopBottom + (2 * cardHeightPoints) - (row * cardHeightPoints);
	            image.setAbsolutePosition(x, y);

	            // Agregar imagen al documento
	            document.add(image);

	            cardCounter++;
	        }
	    }

	    return cardCounter;
	}
 }

