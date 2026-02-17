package org.company.books.backend.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.company.books.backend.model.Categoria;
import org.company.books.backend.model.dao.ICategoriaDAO;
import org.company.books.backend.response.CategoriaResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    @Autowired
    private ICategoriaDAO categoriaDao;

    @Override
    @Transactional(readOnly = true) // Se maneja la transaccion del metodo con esta anotacion, es decir que los datos son confiables,
                                    //el readoOnly indica que la BBDD no se ra modificada y solo se conjeran datos a vinel de vista
    public ResponseEntity<CategoriaResponseRest> buscarCategoria(){

        CategoriaResponseRest response = new CategoriaResponseRest();

        log.info("iniciando metodo buscarCategorias()");
        try{    

            List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();

            response.getCategoriaResponse().setCategoria(categoria);

            response.setMetaData("Respuesta ok", "200", "Repuesta exitosa");

        }catch(Exception e){
            
            response.setMetaData("Repuesta no ok","-1", "Repuesta incorrecta");
            log.error("error al consultar categorias: ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR); //Devuleve un 500

        }
        log.info("Categoria encontrada");
        return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK); //Devuleve un 200
    }

    @Override
    @Transactional(readOnly = true) // Se maneja la transaccion del metodo con esta anotacion, es decir que los datos son confiables,
                                    //el readoOnly indica que la BBDD no se ra modificada y solo se conjeran datos a vinel de vista
    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {

        log.info("Iniciando metodo de buscarPorId()");

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> listaDeCategorias = new ArrayList<>();

        try{

            Optional<Categoria> categoria= categoriaDao.findById(id);

            if (categoria.isPresent()) {
                listaDeCategorias.add(categoria.get());
                response.getCategoriaResponse().setCategoria(listaDeCategorias);
                response.setMetaData("Respuesta ok", "200", "Repuesta exitosa");
            }else{
                log.error("error al consultar categorias: ");
                response.setMetaData("Repuesta no ok","-1", "Categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.NOT_FOUND);
            }

        }catch(Exception e){
            response.setMetaData("Repuesta no ok","-1", "Repuesta incorrecta");
            log.error("error al consultar buscar por id",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR); //Devuleve un 500
        }
        log.info("Categoria encontrada");
        return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);//Devuleve un 200
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {

        log.info("Iniciando metodo de crear categoria");

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> listaDeCategorias = new ArrayList<>();

        try{
            Categoria categoriaGuardada = categoriaDao.save(categoria);
            if (categoriaGuardada!=null) {
                listaDeCategorias.add(categoriaGuardada);
                response.getCategoriaResponse().setCategoria(listaDeCategorias);               
                response.setMetaData("Respuesta ok", "200", "Repuesta exitosa");
            }else{
                log.error("error al consultar crear categoria:");
                response.setMetaData("Repuesta no ok","-1", "Categoria no creada");
                return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            response.setMetaData("Repuesta no ok","-1", "Repuesta incorrecta");
            log.error("error al consultar crear categorias: ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR); //Devuleve un 500
        }
        log.info("Categoria creada");
        return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);//Devuleve un 200
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {

        log.info("Iniciando metodo actualizar()");

        CategoriaResponseRest response =new CategoriaResponseRest();
        List<Categoria> listaDCategorias= new ArrayList<Categoria>();

        try{

            Optional<Categoria> categoriaBuscada=categoriaDao.findById(id);
            if (categoriaBuscada.isPresent()) {
                categoriaBuscada.get().setNombre(categoria.getNombre());
                categoriaBuscada.get().setDescripcion(categoria.getDescripcion());

                Categoria categoriaActualizada = categoriaDao.save(categoriaBuscada.get());

                if (categoriaActualizada!=null) {
                    listaDCategorias.add(categoriaActualizada);
                    response.getCategoriaResponse().setCategoria(listaDCategorias);               
                    response.setMetaData("Respuesta ok", "200", "Repuesta exitosa");
                }else{
                log.error("error al consultar actualizar categoria");
                response.setMetaData("Repuesta no ok","-1", "Categoria no atualizada");
                return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.BAD_REQUEST);                
                }
            }else{
                log.error("error al buscar la categoria");
                response.setMetaData("Repuesta no ok","-1", "Categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.NOT_FOUND);   
            }

        }catch(Exception e){
            response.setMetaData("Repuesta no ok","-1", "Repuesta incorrecta");
            log.error("error al consultar crear categorias: ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR); //Devuleve un 500
        }

        log.info("Categoria actualizada");
        return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> eliminar(Long id){

        log.info("inicinado metodo eliminar");
        CategoriaResponseRest response =new CategoriaResponseRest();
        
        try{
            if (categoriaDao.existsById(id)){
                categoriaDao.deleteById(id);
                response.setMetaData("Repuesta ok", "200", "Categoria eliminada");
            }else{
                response.setMetaData("Repuesta no ok","404", "Categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            response.setMetaData("Repuesta no ok","-1", "Categoria no eliminada");
            log.error("error al consultar eliminar categorias: ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR); //Devuleve un 500
        }
        log.info("Categoria eliminada");
        return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);
    }

}
