package EcoMarketSpa.EcoMarketSpa;

import EcoMarketSpa.EcoMarketSpa.model.Usuario;
import EcoMarketSpa.EcoMarketSpa.model.Usuario.Rol;
import EcoMarketSpa.EcoMarketSpa.model.Envio;
import EcoMarketSpa.EcoMarketSpa.model.Envio.EstadoEnvio;
import EcoMarketSpa.EcoMarketSpa.repository.UsuarioRepository;
import net.datafaker.Faker;
import EcoMarketSpa.EcoMarketSpa.repository.EnvioRepository;

//comentario 
// tercer comentario


import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import java.util.Random;

@Profile("test")
@Component
public class EnvioDataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnvioRepository envioRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        List<Usuario> usuarios = new ArrayList<>();

        // Generar 10 usuarios
        for (int i = 0; i < 10; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombres(faker.name().firstName());
            usuario.setApellidos(faker.name().lastName());
            usuario.setCorreo("usuario" + i + "@correo.com");
            usuario.setEdad(faker.number().numberBetween(18, 65));
            usuario.setContraseña(faker.internet().password(8, 12));
            usuario.setDireccion(faker.address().fullAddress());
            usuario.setRol(Rol.values()[random.nextInt(Rol.values().length)]);

            usuarioRepository.save(usuario);
            usuarios.add(usuario);
        }

        System.out.println("Usuarios generados: " + usuarios.size());

        // Generar 20 envíos
        for (int i = 0; i < 20; i++) {
            Usuario cliente = usuarios.get(random.nextInt(usuarios.size()));

            LocalDate salida = LocalDate.now().minusDays(random.nextInt(5));
            LocalDate entrega = salida.plusDays(random.nextInt(4) + 1);

            Envio envio = new Envio();
            envio.setDireccionDestino(faker.address().fullAddress());
            envio.setFechaSalida(salida.toString());
            envio.setFechaEntrega(entrega.toString());
            envio.setEstado(EstadoEnvio.values()[random.nextInt(EstadoEnvio.values().length)]);
            envio.setCliente(cliente);

            envioRepository.save(envio);
        }

        System.out.println("Envíos generados: 20");
    }
}