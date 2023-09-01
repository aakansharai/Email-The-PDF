package com.example.emailthepdf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfDiv;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String name, TypeOfPassenger, classOfT, email, Invoice, BookingDate, PNR, BookedBy;
    int Basics, YQ, Taxes, Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = "Aakansha Rai";
        TypeOfPassenger = "Adult";
        classOfT = "Economy";
        email = "aakansha80100@gmail.com";
        Invoice = "IIGDJ324";
        BookedBy = "Travbox";
        PNR = "ABCBA78";
        BookingDate = "28-Aug-2023";

        generateInvoice();
    }

    public void generateInvoice() {
        Document document = new Document();
        try {
            File pdfFile = new File(getFilesDir(), "invoice.pdf");
            FileOutputStream fileOutputStream=new FileOutputStream(pdfFile);
            PdfWriter.getInstance(document,fileOutputStream);
            document.open();

            // Add a title
            Font logoName = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titleLogo = new Paragraph("TRIPZY GO", logoName);
            titleLogo.setAlignment(Element.ALIGN_LEFT);
            titleLogo.setSpacingBefore(10f);
            document.add(titleLogo);

            // Add a title
            Font titleInvoice = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph textInvoice = new Paragraph("Invoice", titleInvoice);
            textInvoice.setAlignment(Element.ALIGN_RIGHT);
            textInvoice.setSpacingAfter(10f);
            document.add(textInvoice);

            // Add invoice details
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);

            Font fontCellTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            table.addCell(createCell("TRAVBOX", PdfPCell.ALIGN_LEFT, fontCellTitle));
            table.addCell(createCell("Travbox Test", PdfPCell.ALIGN_RIGHT, fontCellTitle));
            table.addCell(createCell("Head Office Katra", PdfPCell.ALIGN_LEFT));
            table.addCell(createCell("P315A, Unique Park", PdfPCell.ALIGN_RIGHT));
            table.addCell(createCell("Tel: 8929000444", PdfPCell.ALIGN_LEFT));
            table.addCell(createCell("Mobile No: 9163097979", PdfPCell.ALIGN_RIGHT));
            table.addCell(createCell("Email: info@travbox.travel", PdfPCell.ALIGN_LEFT));
            table.addCell(createCell("Email: sales@travbox.in", PdfPCell.ALIGN_RIGHT));
            table.addCell(createCell(" ", PdfPCell.ALIGN_LEFT));
            table.addCell(createCell("GSTIN:", PdfPCell.ALIGN_RIGHT));
            table.addCell(createCell(" ", PdfPCell.ALIGN_LEFT));
            table.addCell(createCell("Pan No: ABCDE2345R", PdfPCell.ALIGN_RIGHT));
            table.addCell(createCell(" ", PdfPCell.ALIGN_LEFT));
            table.addCell(createCell(" ", PdfPCell.ALIGN_RIGHT));

            document.add(table);

            // Header separator
            LineSeparator line = new LineSeparator();
            line.setLineWidth(1);
            line.setPercentage(100);
            line.setAlignment(Element.ALIGN_CENTER);
            line.setLineColor(BaseColor.DARK_GRAY);
            document.add(line);







            // TAX details
            PdfPTable tdTable = new PdfPTable(4);
            tdTable.setWidthPercentage(100);
            tdTable.setSpacingBefore(10f);
            tdTable.setSpacingAfter(10f);

            tdTable.addCell(createCell("Invoice No. :", PdfPCell.ALIGN_LEFT));
            tdTable.addCell(createCell("Booking Date :", PdfPCell.ALIGN_LEFT));
            tdTable.addCell(createCell("PNR :", PdfPCell.ALIGN_LEFT));
            tdTable.addCell(createCell("Booked By :", PdfPCell.ALIGN_LEFT));
            ticketDetails(tdTable, Invoice, BookingDate, PNR, BookedBy);

            document.add(tdTable);

// ----------------------  T R A V E L L E R ' S    D E T A I L --------------------------------------------------

            Paragraph onwards = new Paragraph("Onewords : Kolkata-CCU-Bagdora-IXB, Air AsiaIndia - 582", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            onwards.setSpacingBefore(20f);
            onwards.setSpacingAfter(10f);
            onwards.setAlignment(Element.ALIGN_LEFT);
            document.add(onwards);

            PdfPTable oneword = new PdfPTable(5);
            oneword.setWidthPercentage(100);
            oneword.setSpacingBefore(10f);
            oneword.setSpacingAfter(10f);

            oneword.addCell(createCell("Onewords : Kolkata-CCU-Bagdora-IXB, Air AsiaIndia - 582", PdfPCell.ALIGN_LEFT));
            oneword.addCell(createCell("Travel Date: 04 Sep 2023", PdfPCell.ALIGN_LEFT));


            PdfPTable travInfo = new PdfPTable(7);
            travInfo.setWidthPercentage(100);
            travInfo.setSpacingBefore(10f);
            travInfo.setSpacingAfter(10f);

            travInfo.addCell(createCell("Name", PdfPCell.ALIGN_LEFT));
            travInfo.addCell(createCell("Type", PdfPCell.ALIGN_LEFT));
            travInfo.addCell(createCell("Class", PdfPCell.ALIGN_LEFT));
            travInfo.addCell(createCell("Basics", PdfPCell.ALIGN_LEFT));
            travInfo.addCell(createCell("YQ", PdfPCell.ALIGN_LEFT));
            travInfo.addCell(createCell("Taxes", PdfPCell.ALIGN_LEFT));
            travInfo.addCell(createCell("Total", PdfPCell.ALIGN_LEFT));
            ticketDetails(travInfo, Invoice, BookingDate, PNR, BookedBy);

            document.add(tdTable);


// ---------------------- F O O T E R --------------------------------------------------

            // Taxes
            PdfPTable taxTable = new PdfPTable(2);
            taxTable.setWidthPercentage(33);
            taxTable.setSpacingAfter(10f);
            taxTable.setHorizontalAlignment(Element.ALIGN_RIGHT);

            taxTable.addCell(createCell(" ", PdfPCell.ALIGN_RIGHT));
            taxTable.addCell(createCell(" ", PdfPCell.ALIGN_RIGHT));
            taxTable.addCell(createCell("Basic  :", PdfPCell.ALIGN_RIGHT));
            taxTable.addCell(createCell("986 INR", PdfPCell.ALIGN_RIGHT));
            taxTable.addCell(createCell("Taxes  :", PdfPCell.ALIGN_RIGHT));
            taxTable.addCell(createCell("1,029 INR", PdfPCell.ALIGN_RIGHT));
            taxTable.addCell(createCell("TDS  :", PdfPCell.ALIGN_RIGHT));
            taxTable.addCell(createCell("1 INR", PdfPCell.ALIGN_RIGHT));
            taxTable.addCell(createCell("Commission  :", PdfPCell.ALIGN_RIGHT));
            taxTable.addCell(createCell("-(13) INR", PdfPCell.ALIGN_RIGHT));
            taxTable.addCell(createCell("Grand Total  :", PdfPCell.ALIGN_RIGHT, fontCellTitle));
            taxTable.addCell(createCell("2,015 INR", PdfPCell.ALIGN_RIGHT, fontCellTitle));
            taxTable.addCell(createCell(" ", PdfPCell.ALIGN_RIGHT));
            taxTable.addCell(createCell(" ", PdfPCell.ALIGN_RIGHT));

            document.add(taxTable);

            // Terms And Conditions

            // DIV
            PdfDiv div = new PdfDiv();
//            div.setPercentageWidth(90f);
            div.setKeepTogether(true);

            PdfPTable tc = new PdfPTable(2);
            tc.setWidthPercentage(90);
            tc.setSpacingAfter(10f);
            tc.keepRowsTogether(1,3);
            tc.setKeepTogether(true);
            tc.setHorizontalAlignment(Element.ALIGN_LEFT);

            tc.addCell(tcCell("Terms & Conditions", PdfPCell.ALIGN_CENTER));
            tc.addCell(createCell("For TRAVBOX ", PdfPCell.ALIGN_CENTER));
            tc.addCell(createCell(" ", PdfPCell.ALIGN_CENTER));
            tc.addCell(tcCell(" ", PdfPCell.ALIGN_CENTER));


            div.addElement(tc);

            Paragraph bottomText = new Paragraph("Computer Generated Report, Require No Signature", FontFactory.getFont(FontFactory.HELVETICA, 10));
            bottomText.setSpacingBefore(20f);
            bottomText.setSpacingAfter(10f);
            bottomText.setKeepTogether(true);
            bottomText.setAlignment(Element.ALIGN_CENTER);
            div.addElement(bottomText);

            document.add(div);

            Uri pdfUri = FileProvider.getUriForFile(MainActivity.this, "in.tripzygo.tripzygoflightbookingapp.provider", pdfFile);

            // Path to your generated PDF
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(pdfUri, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private void ticketDetails(PdfPTable tdTable, String invoice, String bookingDate, String pnr, String bookedBy) {
        tdTable.addCell(createCell(invoice, PdfPCell.ALIGN_LEFT));
        tdTable.addCell(createCell(bookingDate, PdfPCell.ALIGN_LEFT));
        tdTable.addCell(createCell(pnr, PdfPCell.ALIGN_LEFT));
        tdTable.addCell(createCell(bookedBy, PdfPCell.ALIGN_LEFT));
    }

    private static PdfPCell createCell(String content, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setExtraParagraphSpace(5f);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
    private static PdfPCell createCell(String content, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(content, FontFactory.getFont(FontFactory.HELVETICA)));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setExtraParagraphSpace(2f);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
    private static PdfPCell tcCell(String content, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(content, FontFactory.getFont(FontFactory.HELVETICA)));
        cell.setExtraParagraphSpace(2f);
        cell.setPaddingTop(20f);
        cell.setPaddingBottom(20f);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
}