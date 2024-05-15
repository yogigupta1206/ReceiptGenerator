package com.yogigupta1206.invoicereceiptmaker.shared.utils.pdf

import com.itextpdf.layout.Document
import com.itextpdf.layout.Style
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Div
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.properties.HorizontalAlignment
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.UnitValue
import com.itextpdf.layout.properties.VerticalAlignment
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.shared.utils.toCurrentDataAsString

class CustomerSection {

    fun createSection(document: Document, customer: Customer, quotation: Quotation) {
        // Left section
        val headingStyle: Style = Style().setBold()

        val customerInfo = Paragraph(Text("To,").addStyle(headingStyle)).add("\n")
            .add(Text(customer.name).addStyle(headingStyle)).add("\n${customer.address1}")
            .add("\n${customer.address2}").add("\n${customer.email}").add("\n${customer.phone}")
            .add("\n${customer.otherInfo}")
        customerInfo.setMultipliedLeading(1.1f)

        val sectionLeft = Div().setHorizontalAlignment(HorizontalAlignment.LEFT).add(customerInfo)


        // Right section
        val internalTable = Table(2).setAutoLayout()
            .addCell(getCell(Paragraph("Quotation#").setBold()))
            .addCell(getCell(Paragraph("Quote-${quotation.id}").setMarginLeft(10f)))
            .addCell(getCell(Paragraph("Date:").setBold()))
            .addCell(
                getCell(
                    Paragraph(quotation.quotationTime.toCurrentDataAsString()).setMarginLeft(
                        10f
                    )
                )
            )
            .setHorizontalAlignment(HorizontalAlignment.RIGHT)

        val sectionRight =
            Div().setHorizontalAlignment(HorizontalAlignment.RIGHT).add(internalTable)


        // Add Both section in a table layout
        val table = Table(UnitValue.createPercentArray(floatArrayOf(50f, 50f))).setAutoLayout()
            .useAllAvailableWidth().addCell(PdfUtils.getCell(sectionLeft, HorizontalAlignment.LEFT))
            .addCell(PdfUtils.getCell(sectionRight, HorizontalAlignment.RIGHT))


        // Add table to document
        document.add(table)
    }

    private fun getCell(
        element: Paragraph
    ): Cell {
        val cell = Cell().add(element.setTextAlignment(TextAlignment.RIGHT))
        cell.setPadding(0f)
        cell.setHorizontalAlignment(HorizontalAlignment.RIGHT)
        cell.setVerticalAlignment(VerticalAlignment.TOP)
        cell.setBorder(Border.NO_BORDER)
        return cell
    }

}