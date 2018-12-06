package pt.ulp.ei.recyclerviewexampleaplication;

class Linguagem {
    private  long id;
    private String designacao;
    private int valor;

    public Linguagem(String designacao) {
        this.id = 0;
        this.designacao = designacao;
        this.valor = 0;
    }

    public Linguagem() {
        this.id = 0;
        this.designacao = "";
        this.valor = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void votar() {
        this.setValor(this.getValor() + 1);
    }
}
