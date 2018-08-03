package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {

    public static final double DELTA = 0.0001;

    @Test
    public void deveRetornarDescricaoValida_quandoReceberDescricao() {
        Leilao console = new Leilao("Console");
        assertEquals("Console", console.getDescricao());
    }

    @Test
    public void deveRetornarMaiorLance_quandoReceberUmLance() {
        Leilao console = new Leilao("Console");
        console.proporLance(new Lance(new Usuario("Alex"), 200.0));

        assertEquals(200.0, console.getMaiorLance(), DELTA);
    }

    @Test
    public void deveRetornarMaiorLance_quandoReceberMaisDeUmLance() {
        Leilao computador = new Leilao("Computador");
        computador.proporLance(new Lance(new Usuario("Alex"), 100.0));
        computador.proporLance(new Lance(new Usuario("Fran"), 200.0));
        computador.proporLance(new Lance(new Usuario("Brayan"), 50.0));

        assertEquals(200.0, computador.getMaiorLance(), DELTA);
    }

    @Test
    public void deveRetornarMenorLance_quandoReceberMaisDeUmLance() {
        Leilao computador = new Leilao("Computador");
        computador.proporLance(new Lance(new Usuario("Alex"), 100.0));
        computador.proporLance(new Lance(new Usuario("Fran"), 200.0));
        computador.proporLance(new Lance(new Usuario("Brayan"), 50.0));

        assertEquals(50.0, computador.getMenorLance(), DELTA);
    }

    @Test
    public void deveRetornarTresMaioresLances_quandoReceberTresLances() {
        Leilao computador = new Leilao("Computador");

        computador.proporLance(new Lance(new Usuario("Alex"), 100.0));
        computador.proporLance(new Lance(new Usuario("Fran"), 200.0));
        computador.proporLance(new Lance(new Usuario("Brayan"), 50.0));

        List<Lance> tresMaioresLances =  computador.getTresMaioresLances();

        assertEquals(3, tresMaioresLances.size());
        assertEquals(200.0, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(100.0, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(50.0, tresMaioresLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deveRetornarTresMaioresLances_quandoNaoReceberLances() {
        Leilao computador = new Leilao("Computador");

        List<Lance> tresMaioresLances = computador.getTresMaioresLances();

        assertEquals(0, tresMaioresLances.size());
    }

    @Test
    public void deveRetornarTresMaioresLances_quandoReceberUmLance() {
        Leilao computador = new Leilao("Computador");
        computador.proporLance(new Lance(new Usuario("Fran"), 200.0));

        List<Lance> tresMaioresLances = computador.getTresMaioresLances();

        assertEquals(1, tresMaioresLances.size());
        assertEquals(200.0, tresMaioresLances.get(0).getValor(), DELTA);
    }

    @Test
    public void deveRetornarTresMaioresLances_quandoReceberDoisLances() {
        Leilao computador = new Leilao("Computador");
        computador.proporLance(new Lance(new Usuario("Alex"), 100.0));
        computador.proporLance(new Lance(new Usuario("Fran"), 200.0));


        List<Lance> tresMaioresLances = computador.getTresMaioresLances();

        assertEquals(2, tresMaioresLances.size());
        assertEquals(200.0, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(100.0, tresMaioresLances.get(1).getValor(), DELTA);
    }

    @Test
    public void deveRetornarTresMaioresLances_quandoReceberCincoLances() {
        Leilao computador = new Leilao("Computador");
        computador.proporLance(new Lance(new Usuario("Alex"), 100.0));
        computador.proporLance(new Lance(new Usuario("Brayan"), 300.0));
        computador.proporLance(new Lance(new Usuario("Else"), 50.0));
        computador.proporLance(new Lance(new Usuario("Fran"), 200.0));
        computador.proporLance(new Lance(new Usuario("Else"), 280.0));


        List<Lance> tresMaioresLances = computador.getTresMaioresLances();

        assertEquals(3, tresMaioresLances.size());
        assertEquals(300.0, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(280.0, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLances.get(2).getValor(), DELTA);
    }



}