import projeto.app.Dataloader;
import projeto.app.Options.MenuRapido;
import projeto.app.Options.ProdutoOptions;
import projeto.printers.Printer;
import projeto.repositorios.ProdutoRepository;
import projeto.repositorios.UsuarioRepository;
import projeto.services.ProdutoService;
import projeto.services.UsuarioService;

import javax.xml.crypto.Data;

void main(){

    //Teste

    ProdutoRepository produtoRepository = new ProdutoRepository();
    UsuarioRepository usuarioRepository = new UsuarioRepository();

    ProdutoService produtoService = new ProdutoService(produtoRepository);
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    Printer printer = new Printer();
    Scanner scanner = new Scanner(System.in);

    MenuRapido menuRapido = new MenuRapido();
    ProdutoOptions produtoOptions = new ProdutoOptions(menuRapido, produtoService,scanner, printer);


    Dataloader dataloader = new Dataloader(produtoService, usuarioService);

    dataloader.iniciar();



}