package br.ifsul.bdii.gui;

import java.time.*;
import java.sql.Date;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JOptionPane;

import br.ifsul.bdii.Starter;
import br.ifsul.bdii.domain.entity.Emprestimo;
import br.ifsul.bdii.domain.entity.Livro;
import br.ifsul.bdii.domain.entity.Usuario;
import br.ifsul.bdii.service.EmprestimoService;
import br.ifsul.bdii.service.UsuarioService;
import br.ifsul.bdii.domain.entity.Avaliacao;
import br.ifsul.bdii.service.AvaliacaoService;

public class UILivro extends JFrame{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel txtTitulo;
    private JLabel txtDescricao;
    private JLabel txtCapaLivro;
    private JTextField txtComentario;
    private JButton btnEnviar;
    private JButton btnComentarios;
    private JButton btnPerfil;
    private JButton btnEmprestimo;
    private JButton btnVoltar;

    private EmprestimoService emprestimoService;
    private AvaliacaoService avaliacaoService;
    private UsuarioService usuarioService;

    public UILivro(Usuario usuario, Livro livro){

        avaliacaoService = Starter._avaliacaoService;
        emprestimoService = Starter._emprestimoService;
        usuarioService = Starter._usuarioService;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1200, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnVoltar = new JButton("<-");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                UIPrincipal UIPri = new UIPrincipal(usuario);
                UIPri.setVisible(true);
                dispose();

            }
        });
        btnVoltar.setBounds(0,0,50,30);
        contentPane.add(btnVoltar);

        txtTitulo = new JLabel(livro.getTitulo());
        txtTitulo.setBounds(65, 20, 150, 30);
        txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(txtTitulo);

        txtDescricao =new JLabel(livro.getDescricao());
        txtDescricao.setBounds(400, 100, 600, 300);
        txtDescricao.setBorder(new LineBorder(Color.BLACK, 1));
        txtDescricao.setHorizontalAlignment(SwingConstants.LEFT);
        contentPane.add(txtDescricao);

        txtCapaLivro = new JLabel("Capa Livro");
        txtCapaLivro.setBounds(40, 100, 200, 300);
        txtCapaLivro.setBorder(new LineBorder(Color.BLACK, 1));
        contentPane.add(txtCapaLivro);

        JLabel lblNewJLabel = new JLabel("Comentario");
        lblNewJLabel.setBounds(401, 460, 90, 30);
        contentPane.add(lblNewJLabel);

        txtComentario = new JTextField();
        txtComentario.setBounds(400, 460, 500, 30);
        contentPane.add(txtComentario);

        btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Avaliacao avaliacao = realizaAvaliacao(txtComentario.getText(), usuario, livro);
                if(avaliacao!=null){
                    JOptionPane.showMessageDialog(contentPane,"Sucesso","Comentario salvo com sucesso!",JOptionPane.INFORMATION_MESSAGE);
                    btnEnviar.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(contentPane,"Erro","Erro em salvar o comentario!",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnEnviar.setBounds(900, 460, 100, 30);
        contentPane.add(btnEnviar);

        btnComentarios = new JButton("Comentarios");
        btnComentarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                UIComentarios uiCom = new UIComentarios(usuario, livro);
                uiCom.setVisible(true);
                dispose();
            }
        });
        btnComentarios.setBounds(400, 500, 600, 30);
        contentPane.add(btnComentarios);

        btnPerfil = new JButton("Perfil");
        btnPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                UIPerfil uiPer = new UIPerfil(usuario, usuario);
                uiPer.setVisible(true);
            }
        });

        btnPerfil.setBounds(650, 20, 90, 30);
        contentPane.add(btnPerfil);

        btnEmprestimo = new JButton("Emprestimo");
        btnEmprestimo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(!isEmprestado(livro)) {
                    JOptionPane.showMessageDialog(contentPane, "ERRO. O livro já foi emprestado", "Erro de emprestimo", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "CERTO. O livro foi emprestado com sucesso", "Sucesso no emprestimo", JOptionPane.INFORMATION_MESSAGE);
                    btnEmprestimo.setEnabled(false);
                    realizaEmprestimo(usuario, livro);
                }
            }
        });
        btnEmprestimo.setBounds(800, 20, 120, 30);
        contentPane.add(btnEmprestimo);
    }

    private Boolean isEmprestado(Livro livro) {
        Emprestimo e = emprestimoService.findByLivroId(livro.getId());

        if(e==null) {
            return true;
        } else {
            return e.getEstado();
        }
    }

    private void realizaEmprestimo(Usuario usuario, Livro livro) {
        Date dataAtual = Date.valueOf(LocalDate.now());
        Date dataDevolucao = Date.valueOf(LocalDate.now().plusWeeks(2));

        Emprestimo e = Emprestimo.builder()
            .dataEmprestimo(dataAtual)
            .dataDevolucao(dataDevolucao)
            .usuario(usuario)
            .livro(livro)
            .estado(false)
            .build();
            
        emprestimoService.save(e);
        usuario.setEmprestimo(true);
        usuarioService.update(usuario, usuario.getId());
    }

    private Avaliacao realizaAvaliacao(String texto, Usuario usuario, Livro livro) {

        Avaliacao avaliacao = Avaliacao.builder()
            .texto(texto)
            .usuario(usuario)
            .livro(livro)
            .build();

        return avaliacaoService.save(avaliacao);
    }
}