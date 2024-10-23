package br.ifsul.bdii.gui;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import br.ifsul.bdii.Starter;
import br.ifsul.bdii.domain.entity.Emprestimo;
import br.ifsul.bdii.domain.entity.Livro;
import br.ifsul.bdii.domain.entity.Usuario;
import br.ifsul.bdii.service.EmprestimoService;
import br.ifsul.bdii.service.UsuarioService;

public class UIPerfil extends JFrame{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel txtFoto;
    private JLabel txtInfo;
    private JLabel txtDescricao;
    private JButton btnVoltar;
    private JButton btnDevolucao;

    private EmprestimoService emprestimoService;
    private UsuarioService usuarioService;

    public UIPerfil (Usuario usuarioAcesso, Usuario usuarioPerfil){

        emprestimoService = Starter._emprestimoService;
        usuarioService = Starter._usuarioService;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1200, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnVoltar = new JButton("<-");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                UIPrincipal UIPri = new UIPrincipal(usuarioAcesso);
                UIPri.setVisible(true);
                dispose();
            }
        });
        btnVoltar.setBounds(0,0,50,30);
        contentPane.add(btnVoltar);

        txtFoto = new JLabel(usuarioPerfil.getNome());
        txtFoto.setBounds(50, 50, 200, 200);
        txtFoto.setBorder(new LineBorder(Color.BLACK,1));
        contentPane.add(txtFoto);

        txtInfo = new JLabel();
        txtInfo.setBounds(50, 300, 300, 200);
        txtInfo.setBorder(new LineBorder(Color.BLACK, 1));
        contentPane.add(txtInfo);

        JLabel txtNome = new JLabel("Nome: " + usuarioPerfil.getNome());
        txtNome.setBounds(50,310,300,30);
        contentPane.add(txtNome);

        JLabel txtEmail = new JLabel("Email: " + usuarioPerfil.getEmail());
        txtEmail.setBounds(50, 340, 300, 30);
        contentPane.add(txtEmail);

        JLabel txtTelefone = new JLabel("Telefone: " + usuarioPerfil.getTelefone());
        txtTelefone.setBounds(50, 370, 300, 30);
        contentPane.add(txtTelefone);

        String desc = buscaDescricao(usuarioPerfil);

        txtDescricao = new JLabel(desc);
        txtDescricao.setBounds(500, 50, 600, 200);
        txtDescricao.setBorder(new LineBorder(Color.BLACK, 1));
        contentPane.add(txtDescricao);

        if(usuarioAcesso.getEmprestimo()) {
            btnDevolucao = new JButton("Devolução do livro");
            btnDevolucao.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    realizaDevolucao(usuarioAcesso);
                    JOptionPane.showMessageDialog(contentPane,"Sucesso","A sua devolução foi um sucesso!",JOptionPane.INFORMATION_MESSAGE);
                    setEnabled(false);
                }
            });
            btnDevolucao.setBounds(300, 50, 100, 30);
            contentPane.add(btnDevolucao);
        }
    }

    private String buscaDescricao(Usuario usuario) {
        if(usuario.getEmprestimo()!=null && usuario.getEmprestimo()) {
            Emprestimo e = emprestimoService.findByUsuarioId(usuario.getId());
            Livro l = e.getLivro();

            return l.getDescricao();
        } else {
            return "Este usuário não possui empréstimo";
        }
    }

    private void realizaDevolucao(Usuario usuario) {
        Emprestimo e = emprestimoService.findByUsuarioId(usuario.getId());
        e.setEstado(false);
        emprestimoService.update(e, e.getId());

        usuario.setEmprestimo(false);
        usuarioService.update(usuario, usuario.getId());
    }

}
