package ListadeTarefas;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

    public class ListaDeTarefasGUI extends JFrame {
        private ArrayList<String> tarefas = new ArrayList<>();
        private JTextField campoTarefa;
        private JList<String> listaTarefas;
        private DefaultListModel<String> listModel;
        private int numeroTarefa = 1;

        public ListaDeTarefasGUI() {
            super("Aplicativo de Lista de Tarefas");

            JPanel painelPrincipal = new JPanel(new BorderLayout());
            painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona margens

            JPanel painelTopo = new JPanel();
            JLabel labelTarefa = new JLabel("Tarefa:");
            labelTarefa.setFont(new Font("Arial", Font.BOLD, 16));
            painelTopo.add(labelTarefa);

            listModel = new DefaultListModel<>();
            listaTarefas = new JList<>(listModel);
            listaTarefas.setFont(new Font("Arial", Font.PLAIN, 14));
            listaTarefas.setBackground(new Color(240, 240, 240)); // Cor de fundo
            listaTarefas.setSelectionBackground(new Color(51, 153, 255)); // Cor de seleção
            JScrollPane scrollPane = new JScrollPane(listaTarefas);

            JPanel painelBotoes = new JPanel();
            painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

            campoTarefa = new JTextField(30);
            campoTarefa.setFont(new Font("Arial", Font.PLAIN, 14));
            JButton botaoAdicionar = new JButton("Adicionar");
            JButton botaoRemover = new JButton("Remover Tarefa");
            JButton botaoListar = new JButton("Listar Tarefas");
            JButton botaoSair = new JButton("Sair");

            botaoAdicionar.setFont(new Font("Arial", Font.PLAIN, 14));
            botaoRemover.setFont(new Font("Arial", Font.PLAIN, 14));
            botaoListar.setFont(new Font("Arial", Font.PLAIN, 14));
            botaoSair.setFont(new Font("Arial", Font.PLAIN, 14));

            botaoAdicionar.setBackground(new Color(0, 153, 51)); // Cor de fundo do botão "Adicionar"
            botaoAdicionar.setForeground(Color.WHITE); // Cor do texto do botão "Adicionar", respectivamente
            botaoRemover.setBackground(new Color(255, 0, 0));
            botaoRemover.setForeground(Color.WHITE);
            botaoListar.setBackground(new Color(0, 102, 204));
            botaoListar.setForeground(Color.WHITE);
            botaoSair.setBackground(new Color(102, 102, 102));
            botaoSair.setForeground(Color.WHITE);

            painelBotoes.add(campoTarefa);
            painelBotoes.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço vertical
            painelBotoes.add(botaoAdicionar);
            painelBotoes.add(botaoRemover);
            painelBotoes.add(botaoListar);
            painelBotoes.add(botaoSair);

            botaoAdicionar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String tarefa = campoTarefa.getText().trim();
                    if (!tarefa.isEmpty()) {
                        adicionarTarefa("Tarefa " + numeroTarefa + ": " + tarefa);
                        campoTarefa.setText("");
                        numeroTarefa++;
                    } else {
                        JOptionPane.showMessageDialog(ListaDeTarefasGUI.this, "Digite uma tarefa válida.");
                    }
                }
            });

            botaoRemover.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = listaTarefas.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        removerTarefa(selectedIndex);
                    } else {
                        JOptionPane.showMessageDialog(ListaDeTarefasGUI.this, "Selecione uma tarefa para remover.");
                    }
                }
            });

            botaoListar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    listarTarefas();
                }
            });

            botaoSair.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            painelPrincipal.add(painelTopo, BorderLayout.NORTH);
            painelPrincipal.add(scrollPane, BorderLayout.CENTER);
            painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

            this.add(painelPrincipal);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(600, 400); // Define um tamanho maior
        }

        private void adicionarTarefa(String tarefa) {
            listModel.addElement(tarefa);
        }

        private void removerTarefa(int index) {
            listModel.remove(index);
        }

        private void listarTarefas() {
            JFrame listaFrame = new JFrame("Lista de Tarefas");
            JList<String> lista = new JList<>(listModel);
            JScrollPane scrollPane = new JScrollPane(lista);
            listaFrame.add(scrollPane);
            listaFrame.pack();
            listaFrame.setLocationRelativeTo(null);
            listaFrame.setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                new ListaDeTarefasGUI();
            });
        }

}
