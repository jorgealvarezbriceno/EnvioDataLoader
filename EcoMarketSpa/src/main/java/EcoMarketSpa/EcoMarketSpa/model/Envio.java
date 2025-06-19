package EcoMarketSpa.EcoMarketSpa.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name="Envio")
public class Envio {

    public enum EstadoEnvio {
    EN_PREPARACION,
    ENVIADO,
    ENTREGADO,
    CANCELADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long envioId;

    @Column(nullable = false)
    private String direccionDestino;

    @Column(nullable = false)
    private String FechaSalida;
    
    @Column(nullable = false)
    private String fechaEntrega;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEnvio estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario cliente;

}
