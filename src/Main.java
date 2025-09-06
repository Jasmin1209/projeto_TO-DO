import models.Task;
import service.Taskservice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
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
            System.out.println("6- Filtrar tarefas por data");
            System.out.println("0- Sair");
            System.out.println("Escolha uma das opções: ");
            try {
                options = sc.nextInt();
                sc.nextLine();

                switch (options) {
                    case 1:
                        System.out.println("Descrição: ");
                        descricion = sc.nextLine();
                        System.out.println("Data (dd/MM/yyyy): ");
                        String data = sc.nextLine();
                        System.out.println("Hora INÍCIO (HH:mm): ");
                        String horaInicio = sc.nextLine();
                        System.out.println("Hora TÉRMINO (HH:mm): ");
                        String horaTermino = sc.nextLine();

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime dateTimeSTART = LocalDateTime.parse(data + " " + horaInicio, formatter);

                        taskservice.adicionarTarefas(descricion, dateTimeSTART, horaTermino);
                        break;
                    case 2:
                        taskservice.listarTarefas();
                        break;
                    case 3:
                        System.out.println("Informe o id da tarefa para completar: ");
                        int idToCompletecTask = sc.nextInt();
                        taskservice.completarTarefas(idToCompletecTask);
                        break;
                    case 4:
                        System.out.println("Informe o id da tarefa para ser removida: ");
                        int idToRemoveTask = sc.nextInt();
                        taskservice.removerTarefa(idToRemoveTask);
                        break;
                    case 5:
                        System.out.println("Informe o id da tarefa para ser alterada: ");
                        int idToAlterDescricion = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Informe a nova descrição: ");
                        String newDescricion = sc.nextLine();
                        taskservice.alterarTarefa(idToAlterDescricion, newDescricion);
                        break;
                    case 6:
                        DateTimeFormatter formatterToFilter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                        System.out.println("Informe a data: ");
                        String dataParaFiltro = sc.nextLine();

                        LocalDate dataParaFiltrar = LocalDate.parse(dataParaFiltro, formatterToFilter);
                        List<Task> results = taskservice.filtrarPorData(dataParaFiltrar);

                        if(results.isEmpty()){
                            System.out.println("Data sem tarefas adicionadas");
                        }else{
                            results.forEach(System.out::println);
                        }
                        break;
                    case 0:
                        System.out.println("Saindo do sistema....");
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente...");
                        break;
                }
            }catch (InputMismatchException e){ //InputMismatchException: quando o dado inserido não correponde com o tipo da variável
                System.out.println("Entrada inválida, digite apenas números");
                sc.nextLine();
            }catch (NoSuchElementException e){
                System.out.println(e.getMessage());
            }catch (Exception e){
                System.out.println("Erro: " + e.getMessage());
            }


        }while (options != 0);

        sc.close();
    }
}
