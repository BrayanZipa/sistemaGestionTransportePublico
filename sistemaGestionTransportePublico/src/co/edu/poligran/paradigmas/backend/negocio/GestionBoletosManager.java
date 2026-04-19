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
     * @throws IllegalArgumentException si el objeto es inválido
     */
    public List<BoletoVO> agregarBoleto(BoletoVO b){
        if (b == null) {
            throw new IllegalArgumentException("El boleto no puede ser nulo.");
        }

        if (b.getCodigo() <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (b.getNumeroAsiento() == null || b.getNumeroAsiento().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de asiento no puede ser nulo o vacío.");
        }

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
     * @throws IllegalArgumentException si el código es inválido
     * @throws IllegalStateException si no hay boletos registrados
     */
    public BoletoVO buscarBoletoPorCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (listaBoletos.isEmpty()) {
            throw new IllegalStateException("No hay boletos registrados.");
        }

        for(BoletoVO b : listaBoletos) {
            if(b.getCodigo() == codigo) {
                return b;
            }
        }
        return null;
    }

    /**
     * Método que permite obtener la posición de un boleto por código
     * @param codigo identificador del boleto
     * @return índice o -1 si no existe
     * @throws IllegalArgumentException si el código es inválido
     * @throws IllegalStateException si no hay boletos registrados
     */
    public int obtenerIndicePorCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (listaBoletos.isEmpty()) {
            throw new IllegalStateException("No hay boletos registrados.");
        }

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
     * @return boleto anterior
     * @throws IllegalArgumentException si el objeto es inválido
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public BoletoVO actualizarBoleto(int indice, BoletoVO b){
        if (b == null) {
            throw new IllegalArgumentException("El boleto no puede ser nulo.");
        }

        if (b.getCodigo() <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (indice < 0 || indice >= listaBoletos.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaBoletos.size()
            );
        }
        return listaBoletos.set(indice, b);
    }

    /**
     * Método que permite actualizar un boleto por código
     * @param codigo identificador del boleto
     * @param b nuevo boleto
     * @return true si se actualizó correctamente, false si no existe
     * @throws IllegalArgumentException si los datos son inválidos
     */
    public boolean actualizarBoletoPorCodigo(int codigo, BoletoVO b){
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (b == null) {
            throw new IllegalArgumentException("El boleto no puede ser nulo.");
        }

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
     * @throws IllegalStateException si no hay boletos registrados
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public void eliminarBoleto(int indice){
        if (listaBoletos.isEmpty()) {
            throw new IllegalStateException("No hay boletos para eliminar.");
        }

        if(indice < 0 || indice >= listaBoletos.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaBoletos.size()
            );
        }
        listaBoletos.remove(indice);
    }

    /**
     * Método que permite eliminar un boleto por código
     * @param codigo identificador del boleto
     * @return true si se eliminó, false si no existe
     * @throws IllegalArgumentException si el código es inválido
     */
    public boolean eliminarBoletoPorCodigo(int codigo){
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        int indice = obtenerIndicePorCodigo(codigo);

        if(indice != -1) {
            listaBoletos.remove(indice);
            return true;
        }
        return false;
    }
}