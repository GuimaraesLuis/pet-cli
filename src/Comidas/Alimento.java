package Comidas;
public abstract class Alimento {
    protected String nome;
    protected double quantidade;
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

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public AlimentoTipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(AlimentoTipoEnum tipo) {
        this.tipo = tipo;
    }
}
