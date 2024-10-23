package br.ifsul.bdii.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import br.ifsul.bdii.Starter;
import br.ifsul.bdii.domain.entity.Avaliacao;
import br.ifsul.bdii.domain.entity.Livro;
import br.ifsul.bdii.domain.entity.Usuario;
import br.ifsul.bdii.service.AvaliacaoService;

public class UIComentarios extends JFrame{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel txtComentarios;
    private JButton btnPerfil;
    private JButton btnVerPerfil;
    private JButton btnVoltar;

    private AvaliacaoService avaliacaoService;

    public UIComentarios(Usuario usuario, Livro livro){

        avaliacaoService = Starter._avaliacaoService;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1200, 900);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnVoltar = new JButton("<-");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                UILivro UILri = new UILivro(usuario, livro);
                UILri.setVisible(true);
                dispose();

            }
        });
        btnVoltar.setBounds(0,0,50,30);
        contentPane.add(btnVoltar);

        btnPerfil = new JButton("Perfil");
        btnPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                UIPerfil uiPer = new UIPerfil(usuario, usuario);
                uiPer.setVisible(true);
            }
        });
        btnPerfil.setBounds(1050, 20, 90, 30);
        contentPane.add(btnPerfil);


        List<Avaliacao> listaAvaliacaos = avaliacaoService.findByLivroId(livro.getId());
        inicializarComentarios(usuario, listaAvaliacaos);
    }

    private void inicializarComentarios(Usuario usuario, List<Avaliacao> comentarios) {
        Integer k = 0; 

        for (int i = contentPane.getComponentCount() - 1; i > 3; i--) {
            if (contentPane.getComponent(i) instanceof JButton) {
                contentPane.remove(i);
            }
        }

        for (Avaliacao a : comentarios) {
            txtComentarios = new JLabel(a.getTexto());
            txtComentarios.setBounds(200, 120+(k*100), 650, 75);
            txtComentarios.setBorder(new LineBorder(Color.BLACK,1));
            contentPane.add(txtComentarios);

            btnVerPerfil = new JButton(a.getUsuario().getNome());
            btnVerPerfil.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    UIPerfil uiPer = new UIPerfil(usuario, a.getUsuario());
                    uiPer.setVisible(true);
                    dispose();
                }
            });
                btnVerPerfil.setBounds(60, 120+20+(k*100), 100, 30);
                contentPane.add(btnVerPerfil);

                k++;
            }

            contentPane.revalidate(); 
            contentPane.repaint(); 
        }

}
