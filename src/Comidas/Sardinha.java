package Comidas;
public class Sardinha extends Alimento{
    public Sardinha() {
        this.setPoints(15);
        this.setNome("Sardinha");
        this.setCusto(25);
        this.setTipo(AlimentoTipoEnum.Peixes);
    }
}
