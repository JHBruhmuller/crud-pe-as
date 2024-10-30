package crud;

import java.util.List;
import java.util.Scanner;

public class PecaController {
    private PecaService pecaService = new PecaService();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\nEscolha uma operação: ");
            System.out.println("1 - Adicionar Peça");
            System.out.println("2 - Buscar Peça");
            System.out.println("3 - Atualizar Peça");
            System.out.println("4 - Remover Peça");
            System.out.println("5 - Listar Peças");
            System.out.println("0 - Sair");
            System.out.print("Opção: \n");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> adicionarPeca();
                case 2 -> buscarPeca();
                case 3 -> atualizarPeca();
                case 4 -> removerPeca();
                case 5 -> listarPecas();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void adicionarPeca() {
        System.out.print("Tipo de peça: \n 1 - Genuína \n 2 - Segunda Linha \n Qual?");
        int tipo = scanner.nextInt();
        System.out.print("Código: ");
        int codigo = scanner.nextInt();
        System.out.print("Nome: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        Peca peca;
        if (tipo == 1) {
            peca = new PecaGenuina(codigo, nome, preco);
        } else {
            peca = new PecaSegundaLinha(codigo, nome, preco);
        }

        try {
            pecaService.adicionarPeca(peca);
            System.out.println("Peça adicionada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar peça: " + e.getMessage());
        }
    }

    private void buscarPeca() {
        System.out.print("Código da peça: ");
        int codigo = scanner.nextInt();
        try {
            Peca peca = pecaService.buscarPeca(codigo);
            System.out.println("Peça encontrada: " + peca.getNome() + ", Preço: " + peca.calcularPreco());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void atualizarPeca() {
        System.out.print("Código da peça para atualizar: ");
        int codigo = scanner.nextInt();
        System.out.print("Novo nome: ");
        scanner.nextLine(); 
        String nome = scanner.nextLine();
        System.out.print("Novo preço: ");
        double preco = scanner.nextDouble();
        
        Peca peca;
        try {
            peca = pecaService.buscarPeca(codigo);
            if (peca instanceof PecaGenuina) {
                peca = new PecaGenuina(codigo, nome, preco);
            } else {
                peca = new PecaSegundaLinha(codigo, nome, preco);
            }
            pecaService.atualizarPeca(peca);
            System.out.println("Peça atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar peça: " + e.getMessage());
        }
    }

    private void removerPeca() {
        System.out.print("Código da peça para remover: ");
        int codigo = scanner.nextInt();
        try {
            pecaService.removerPeca(codigo);
            System.out.println("Peça removida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover peça: " + e.getMessage());
        }
    }

    private void listarPecas() {
        List<Peca> pecas = pecaService.listarPecas();
        if (pecas.isEmpty()) {
            System.out.println("Nenhuma peça encontrada.");
        } else {
            System.out.println("Lista de Peças:");
            for (Peca peca : pecas) {
                System.out.println("Código: " + peca.getCodigo() + ", Nome: " + peca.getNome() + ", Preço: " + peca.calcularPreco());
            }
        }
    }
}
