package com.yogigupta1206.invoicereceiptmaker.shared.utils.pdf

import com.itextpdf.layout.Document
import com.itextpdf.layout.Style
import com.itextpdf.layout.element.Div
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.properties.HorizontalAlignment
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.UnitValue
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.model.Business

class BusinessSection {

    fun createSection(document: Document, business: Business, docType: String = "Quotation") {
        // Left section
        val headingStyle: Style = Style()
            .setFontSize(14f)
            .setBold()

        val businessInfo = Paragraph(Text(business.businessName).addStyle(headingStyle))
            .add("\n${business.contactName}")
            .add("\n${business.address1}")
            .add("\n${business.address2}")
            .add("\n${business.address3}")
            .add("\n${business.otherInfo}")
            .add("\n${business.email}")
            .add("\n${business.phone}")
        businessInfo.setMultipliedLeading(1.1f)

        val sectionLeft = Div()
            .setHorizontalAlignment(HorizontalAlignment.LEFT)
            .add(businessInfo)


        // Right section
        val docTypeInPdf: Paragraph = Paragraph(Text(docType).addStyle(headingStyle))
            .setTextAlignment(TextAlignment.RIGHT)

        val sectionRight = Div()
            .setHorizontalAlignment(HorizontalAlignment.RIGHT)
            .add(docTypeInPdf)


        // Add Both section in a table layout
        val table = Table(UnitValue.createPercentArray(floatArrayOf(50f, 50f)))
            .setAutoLayout()
            .useAllAvailableWidth()
            .addCell(PdfUtils.getCell(sectionLeft, HorizontalAlignment.LEFT))
            .addCell(PdfUtils.getCell(sectionRight, HorizontalAlignment.RIGHT))


        // Add table to document
        document.add(table)
    }
}