package utils;

import model.PaymentDetails;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName;

import java.io.File;

public class ReceiptGenerator {
    public static File generate(PaymentDetails details) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A5);
            document.addPage(page);

            PDPageContentStream content = new PDPageContentStream(document, page);
            content.setFont(new PDType1Font(FontName.HELVETICA), 14);
            content.beginText();
            content.setLeading(20f);
            content.newLineAtOffset(50, 600);
            content.showText("✅ LMS Payment Receipt");
            content.newLine();
            content.showText("Payment Type: " + details.getType());
            content.newLine();
            content.showText("Details: " + details.getMaskedInfo());
            content.newLine();
            content.endText();
            content.close();

            File file = new File("receipt.pdf");
            document.save(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
