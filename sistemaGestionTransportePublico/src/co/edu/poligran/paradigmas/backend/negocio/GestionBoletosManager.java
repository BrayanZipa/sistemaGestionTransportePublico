package co.edu.poligran.paradigmas.backend.negocio;

import java.util.ArrayList;
import java.util.List;
import co.edu.poligran.paradigmas.backend.vo.BoletoVO;

/**
 * Clase encargada de gestionar el CRUD de boletos
 */
public class GestionBoletosManager {

    List<BoletoVO> listaBoletos = new ArrayList<>();

    /**
     * Método que permite agregar boletos al catálogo
     * @param b objeto de tipo BoletoVO que se va a agregar
     * @return la lista de boletos
     */
    public List<BoletoVO> agregarBoleto(BoletoVO b){
        listaBoletos.add(b);
        return listaBoletos;
    }

    /**
     * Método que permite obtener todos los boletos
     * @return lista de boletos
     */
    public List<BoletoVO> obtenerBoletos(){
        return listaBoletos;
    }

    /**
     * Método que permite buscar un boleto por código
     * @param codigo identificador del boleto
     * @return el boleto encontrado o null
     */
    public BoletoVO buscarBoletoPorCodigo(int codigo) {
        BoletoVO respuesta = null;
        for(BoletoVO b : listaBoletos) {
            if(b.getCodigo() == codigo) {
                respuesta = b;
                break;
            }
        }
        return respuesta;
    }

    /**
     * Método que permite obtener la posición de un boleto por código
     * @param codigo identificador del boleto
     * @return índice o -1 si no existe
     */
    public int obtenerIndicePorCodigo(int codigo) {
        for(int i = 0; i < listaBoletos.size(); i++) {
            if(listaBoletos.get(i).getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Método que permite actualizar un boleto por índice
     * @param indice posición en la lista
     * @param b nuevo boleto
     * @return boleto anterior o null si no existe
     */
    public BoletoVO actualizarBoleto(int indice, BoletoVO b){
        if(indice >= 0 && indice < listaBoletos.size()) {
            return listaBoletos.set(indice, b);
        }
        return null;
    }

    /**
     * Método que permite actualizar un boleto por código
     * @param codigo identificador del boleto
     * @param b nuevo boleto
     * @return true si se actualizó correctamente, false si no existe
     */
    public boolean actualizarBoletoPorCodigo(int codigo, BoletoVO b){
        int indice = obtenerIndicePorCodigo(codigo);
        if(indice != -1) {
            listaBoletos.set(indice, b);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar un boleto por índice
     * @param indice posición en la lista
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarBoleto(int indice){
        if(indice >= 0 && indice < listaBoletos.size()) {
            listaBoletos.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar un boleto por código
     * @param codigo identificador del boleto
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarBoletoPorCodigo(int codigo){
        int indice = obtenerIndicePorCodigo(codigo);
        if(indice != -1) {
            listaBoletos.remove(indice);
            return true;
        }
        return false;
    }
}