package com.yogigupta1206.invoicereceiptmaker.shared.utils.pdf

import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.borders.DashedBorder
import com.itextpdf.layout.borders.SolidBorder
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.properties.HorizontalAlignment
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.VerticalAlignment
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItemWithProduct

class ItemsSection {

    fun createSection(document: Document, quotationItems: List<QuotationItemWithProduct>) {

        val table = Table(8).useAllAvailableWidth().setAutoLayout()
        table.addHeaderCell(getHeaderCell("#"))
        table.addHeaderCell(getHeaderCell("DESCRIPTION", TextAlignment.LEFT))
        table.addHeaderCell(getHeaderCell("HSN", TextAlignment.LEFT))
        table.addHeaderCell(getHeaderCell("QTY"))
        table.addHeaderCell(getHeaderCell("PRICE"))
        table.addHeaderCell(getHeaderCell("DISCOUNT"))
        table.addHeaderCell(getHeaderCell("GST"))
        table.addHeaderCell(getHeaderCell("TOTAL"))

        quotationItems.forEachIndexed { index, item ->
            val product = item.quotationItem
            table.addCell(getCell((index + 1).toString()))
            table.addCell(
                getProductCell(
                    item.product.name.toString(), item.product.description.toString()
                )
            )
            table.addCell(
                getCell(
                    if (item.product.hsnCode.isNullOrEmpty()) "-" else item.product.hsnCode.toString(),
                    textAlignment = TextAlignment.LEFT
                )
            )
            table.addCell(getCell(product.quantity.toString()))
            table.addCell(getCell(product.price.toString()))
            table.addCell(getCell(product.discount.toString()))
            table.addCell(getCell(product.totalGst.toString()))
            table.addCell(getCell((product.totalAmount).toString()))
        }

        document.add(table)

        val totalTable = Table(2).setAutoLayout().setHorizontalAlignment(HorizontalAlignment.RIGHT)

        totalTable.addCell(getCell("Subtotal: ", false))
        totalTable.addCell(
            getCell(
                String.format("%.2f", quotationItems.sumOf { it.quotationItem.totalAmount }),
                false,
                leftMarginRequired = true
            )
        )

        totalTable.addCell(getCell("Total Discount: ", false))
        totalTable.addCell(
            getCell(
                String.format("%.2f", quotationItems.sumOf { it.quotationItem.discount }),
                false,
                leftMarginRequired = true
            )
        )

        totalTable.addCell(getCell("Total GST: ", false))
        totalTable.addCell(
            getCell(
                String.format("%.2f", quotationItems.sumOf { it.quotationItem.totalGst }),
                false,
                leftMarginRequired = true
            )
        )

        totalTable.addFooterCell(getHeaderCell("Total: "))
        totalTable.addFooterCell(
            getHeaderCell(
                String.format("%.2f", quotationItems.sumOf { it.quotationItem.totalAmount }),
                leftMarginRequired = true
            )
        )

        document.add(totalTable)

    }

    private fun getHeaderCell(
        text: String, textAlignment: TextAlignment = TextAlignment.RIGHT,
        leftMarginRequired: Boolean = false
    ): Cell {
        val data = Paragraph(text).setBold().setTextAlignment(textAlignment)
        if (leftMarginRequired) data.setMarginLeft(20f)

        return Cell().add(data).setBorder(Border.NO_BORDER)
            .setBackgroundColor(ColorConstants.LIGHT_GRAY)
            .setBorderTop(SolidBorder(ColorConstants.BLACK, 1f))
            .setBorderBottom(SolidBorder(ColorConstants.BLACK, 1f))
            .setMarginTop(1f)
            .setMarginBottom(1f)

    }

    private fun getProductCell(
        productName: String,
        productDesc: String,
    ): Cell {
        return Cell().add(
            Paragraph(
                Text(productName).setBold()
            ).add("\n$productDesc").setTextAlignment(TextAlignment.LEFT)
        ).setBorder(Border.NO_BORDER).setBorderBottom(DashedBorder(ColorConstants.BLACK, 1f))

    }

    private fun getCell(
        text: String, bottomBorder: Boolean = true,
        leftMarginRequired: Boolean = false,
        textAlignment: TextAlignment = TextAlignment.RIGHT
    ): Cell {
        val data = Paragraph(text)
            .setTextAlignment(textAlignment)
        if (leftMarginRequired) data.setMarginLeft(20f)

        val cell = Cell().add(data)
        cell.setPadding(0f)
        cell.setHorizontalAlignment(HorizontalAlignment.RIGHT)
        cell.setVerticalAlignment(VerticalAlignment.TOP)
        cell.setBorder(Border.NO_BORDER)
        if (bottomBorder) cell.setBorderBottom(DashedBorder(ColorConstants.BLACK, 1f))
        return cell
    }


}