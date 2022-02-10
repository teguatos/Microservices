package shoppingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoppingservice.entity.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}