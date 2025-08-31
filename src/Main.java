import service.Taskservice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Taskservice taskservice = new Taskservice();

        int options = -1;
        String descricion;


        do {
            System.out.println("--- BEM-VINDO AO TAREFAS DIÁRIAS ---");
            System.out.println("1- Adicionar Tarefas");
            System.out.println("2- Listar Tarefas");
            System.out.println("3- Completar Tarefas");
            System.out.println("4- Remover Tarefas");
            System.out.println("5- Alterar descrição das Tarefas");
            System.out.println("Escolha uma das opções: ");
            options = sc.nextInt();
            sc.nextLine();

            switch (options) {
                case 1 :
                    System.out.println("Descrição: ");
                    descricion = sc.nextLine();
                    taskservice.adicionarTarefas(descricion);
                    break;
                case 2 :
                    taskservice.listarTarefas();
                    break;
                case 3 :
                    System.out.println("Informe o id da tarefa para completar: ");
                    int idToCompletecTask = sc.nextInt();
                    taskservice.completarTarefas(idToCompletecTask);
                    break;
                case 4 :
                    System.out.println("Informe o id da tarefa para ser removida: ");
                    int idToRemoveTask = sc.nextInt();
                    taskservice.removerTarefa(idToRemoveTask);
                    break;
                case 5 :
                    System.out.println("Informe o id da tarefa para ser alterada: ");
                    int idToAlterDescricion = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Informe a nova descrição: ");
                    String newDescricion = sc.nextLine();
                    taskservice.alterarTarefa(idToAlterDescricion, newDescricion);
                    break;
                case 0 :
                    System.out.println("Saindo do sistema....");
                    break;
                default :
                    System.out.println("Opção inválida, tente novamente...");
                    break;
            }

        }while (options != 0);

        sc.close();
    }
}
