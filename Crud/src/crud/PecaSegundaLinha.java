package crud;

public class PecaSegundaLinha extends Peca {
    public PecaSegundaLinha(int codigo, String nome, double preco) {
        super(codigo, nome, preco);
    }

    @Override
    public double calcularPreco() {
        return getPreco();
    }
}