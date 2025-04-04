/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.*;
import java.util.*;
import modelo.Empleado;
import utilidades.Conexion;

/**
 *
 * @author Oscar
 */
public class EmpleadoDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<Empleado> ListarTodos() {
        ArrayList<Empleado> lista = new ArrayList<>();

        try {
            cn = Conexion.Conectar();
            String sql = "select * FROM empleados";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Empleado obj = new Empleado();
                obj.setId(rs.getInt("id"));
                obj.setNombre(rs.getString("nombre"));
                obj.setApellido(rs.getString("apellido"));
                obj.setTelefono(rs.getString("telefono"));
                lista.add(obj);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
        }

        return lista;
    }

    public int registrar(Empleado obj) {
        int result = 0;

        try {
            cn = Conexion.Conectar();
            String sql = "INSERT INTO empleados (nombre, apellido, telefono)"
                    + "values (?,?,?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getApellido());
            ps.setString(3, obj.getTelefono());

            result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
        }

        return result;
    }

    public int editar(Empleado obj) {
        int result = 0;

        try {
            cn = Conexion.Conectar();
            String sql = "UPDATE empleados SET nombre=?, apellido=?, telefono=?"
                    + " WHERE id=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getApellido());
            ps.setString(3, obj.getTelefono());
            ps.setInt(4, obj.getId());
            result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
        }

        return result;
    }

    public int eliminar(int id) {
        int result = 0;

        try {
            cn = Conexion.Conectar();
            String sql = "DELETE FROM empleados WHERE id = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
        }

        return result;
    }

    public Empleado buscarPorId(int id) {
        Empleado obj = null;

        try {
            cn = Conexion.Conectar();
            String sql = "select * FROM empleados where id = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                obj = new Empleado();
                obj.setId(rs.getInt("id"));
                obj.setNombre(rs.getString("nombre"));
                obj.setApellido(rs.getString("apellido"));
                obj.setTelefono(rs.getString("telefono"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
        }

        return obj;
    }
}
