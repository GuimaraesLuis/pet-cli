package Comidas;
public class Pedigree extends Alimento {
    public Pedigree() {
        this.setPoints(60);
        this.setNome("Ração Pedigree");
        this.setTipo(AlimentoTipoEnum.Racao);
        this.setCusto(80);
    }
}
