package EcoMarketSpa.EcoMarketSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EcoMarketSpa.EcoMarketSpa.model.Envio;

import java.util.List;

@Repository
public interface EnvioRepository extends JpaRepository <Envio, Long>{
    List<Envio> findByEstado(String estado);
}
