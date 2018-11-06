package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto taytettyVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        taytettyVarasto = new Varasto(10, 5);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoSopivastiTäydenVaraston() {
        assertEquals(5, taytettyVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void voidaankoOttaaTaiLisätäLiikaa() {

        assertEquals(0, varasto.otaVarastosta(11), vertailuTarkkuus);
        varasto.lisaaVarastoon(8);
        assertEquals(8, varasto.otaVarastosta(11), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.otaVarastosta(11), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void tulostuukoOikein() {

        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
        assertEquals("saldo = 5.0, vielä tilaa 5.0", taytettyVarasto.toString());
    }

    @Test
    public void kayttoKelvottomatVarastot() {

        Varasto huonoVarasto = new Varasto(-5);
        assertEquals(0, huonoVarasto.getTilavuus(), vertailuTarkkuus);

        Varasto huonoTaytettyVarasto = new Varasto(-5, -6);
        assertEquals(0, huonoTaytettyVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, huonoTaytettyVarasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void negatiivisetLisayksetJaPoistot() {

        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-1);
        assertEquals(5, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        varasto.otaVarastosta(-1);
        assertEquals(5, varasto.paljonkoMahtuu(), vertailuTarkkuus);

    }

    @Test
    public void liianTaydenVarastonLuonti() {

        Varasto yliKuormitettuVarasto = new Varasto(10, 11);
        assertEquals(10, yliKuormitettuVarasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0, yliKuormitettuVarasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}
