package com.yogigupta1206.invoicereceiptmaker.shared.utils.pdf

import android.content.Context
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.IBlockElement
import com.itextpdf.layout.element.LineSeparator
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.properties.HorizontalAlignment
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.VerticalAlignment
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItemWithProduct
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.model.Business
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import java.io.File


object PdfUtils {

    const val EXTENSION = ".pdf"
    const val A4_WIDTH = 595
    const val A4_HEIGHT = 842
    const val A4_WIDTH_PX = 793

    @Throws(java.lang.Exception::class)
    fun generatePdfReceipt(
        context: Context,
        business: Business = Business(),
        customer: Customer = Customer(),
        quotationItems: List<QuotationItemWithProduct> = emptyList(),
        quotation: Quotation = Quotation(),
        docType: String = "Quotation"
    ) {

        val dest = File(context.cacheDir, "receipt.pdf")
        val pdfDoc = com.itextpdf.kernel.pdf.PdfDocument(PdfWriter(dest))
        val document = Document(pdfDoc, PageSize.A4)

        document.setMargins(20f, 40f, 20f, 40f)

        // Add business details
        BusinessSection().createSection(document, business, docType)

        // Add line separator
        document.add(LineSeparator(SolidLine(1f)).setMarginTop(5f).setMarginBottom(5f))

        // Add customer details
        CustomerSection().createSection(document, customer, quotation)

        // Add line separator
        document.add(Paragraph("\n"))

        // After customer section
        val afterCustomerSection = Paragraph()
            .add("Dear Sir/Madam,")
            .add("\nThank You for your valuable inquiry. We are pleased to quote you the following:")
        document.add(afterCustomerSection)

        // Add Items Section
        ItemsSection().createSection(document, quotationItems)

        // Add line separator
        document.add(Paragraph("\n"))

        // Add Footer
        val footerSection = Paragraph()
            .add("We hope you find our offer to be in line with your requirements.")
        document.add(footerSection)

        // Add line separator
        document.add(Paragraph("\n"))

        // Add Signature
        val signatureSection = Paragraph()
            .add("For, ${business.businessName}")
            .add("\n\n\n")
            .add("Authorized Signature")
            .setBold()
            .setFontSize(12f)
            .setTextAlignment(TextAlignment.RIGHT)
        //.setHorizontalAlignment(HorizontalAlignment.RIGHT)
        document.add(signatureSection)


        document.close()

    }


    fun getCell(
        element: IBlockElement,
        align: HorizontalAlignment = HorizontalAlignment.LEFT
    ): Cell {
        val cell = Cell().add(element)
        cell.setPadding(0f)
        cell.setHorizontalAlignment(align)
        cell.setVerticalAlignment(VerticalAlignment.MIDDLE)
        cell.setBorder(Border.NO_BORDER)
        return cell
    }


}