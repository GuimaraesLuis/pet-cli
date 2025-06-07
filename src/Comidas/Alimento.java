package Comidas;
public abstract class Alimento {
    protected String nome;
    protected float custo;
    protected int points;
    protected AlimentoTipoEnum tipo;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }
    public AlimentoTipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(AlimentoTipoEnum tipo) {
        this.tipo = tipo;
    }
}
