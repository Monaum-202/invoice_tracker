package com.example.invoice.mapper;

import com.example.invoice.dto.InvoiceDTO;
import com.example.invoice.dto.InvoiceItemDTO;
import com.example.invoice.entity.Invoice;
import com.example.invoice.entity.InvoiceItem;
import org.springframework.stereotype.Component;

@Component
public class InvoiceItemMapper {

    public InvoiceItemDTO toDTO(InvoiceItem item) {
        if (item == null) {
            return null;
        }

        return new InvoiceItemDTO(
                item.getId(),
                item.getItemName(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getTotalPrice(),
                item.getInvoice().getId()  // Pass only the invoiceId
        );
    }


    public InvoiceItem toEntity(InvoiceItemDTO dto, Invoice invoice) {
        if (dto == null) {
            return null;
        }

        InvoiceItem item = new InvoiceItem();
        item.setId(dto.getId());
        item.setItemName(dto.getItemName());
        item.setQuantity(dto.getQuantity());
        item.setUnitPrice(dto.getUnitPrice());
        item.setTotalPrice(dto.getTotalPrice());
        item.setInvoice(invoice);
        return item;
    }
}

