package br.ifsul.bdii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ifsul.bdii.gui.UIInicio;
import br.ifsul.bdii.service.impl.AutorServiceImpl;
import br.ifsul.bdii.service.impl.AvaliacaoServiceImpl;
import br.ifsul.bdii.service.impl.EmprestimoServiceImpl;
import br.ifsul.bdii.service.impl.LivroServiceImpl;
import br.ifsul.bdii.service.impl.UsuarioServiceImpl;
import jakarta.annotation.PostConstruct;

@Component
public class Starter {

    @Autowired
    private UsuarioServiceImpl usuarioService;
    public static UsuarioServiceImpl _usuarioService;

    @Autowired
    private LivroServiceImpl livroService;
    public static LivroServiceImpl _livroService;

    @Autowired
    private EmprestimoServiceImpl emprestimoService;
    public static EmprestimoServiceImpl _emprestimoService;

    @Autowired
    private AvaliacaoServiceImpl avaliacaoService;
    public static AvaliacaoServiceImpl _avaliacaoService;

    @Autowired
    private AutorServiceImpl autorService;
    public static AutorServiceImpl _autorService;
    
    @PostConstruct
    public void run()
    {
        Starter._usuarioService = usuarioService;
        Starter._livroService = livroService;
        Starter._emprestimoService = emprestimoService;
        Starter._avaliacaoService = avaliacaoService;
        Starter._autorService = autorService;

        UIInicio inicio = new UIInicio();
        inicio.setVisible(true);
    }

}
