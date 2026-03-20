package imperio.imperio_backend.sales_order.module;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales_orders")
@Getter
@Setter
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    private String ledgerName;

    private LocalDate orderDate;

    private String stockCategory;

    private String itemName;

    private Integer itemQuantity;

    private String uom;

    private BigDecimal itemRate;

    private BigDecimal discountPercentage;

    private BigDecimal discountAmount;

    private BigDecimal itemNetRate;

    private BigDecimal itemNetAmount;

    private BigDecimal vatPercentage;

    private BigDecimal totalAmount;

    private BigDecimal GrossItemQuantity;

    private BigDecimal grossTotalNetAmount;

    private BigDecimal grossTotalAmount;

    @Column(length = 1000)
    private String narration;

    private String orderPlacedBy;

    private String orderApprovedBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime updatedTime;
}
