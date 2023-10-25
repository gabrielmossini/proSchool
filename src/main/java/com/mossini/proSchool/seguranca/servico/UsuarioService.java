package com.mossini.proSchool.seguranca.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mossini.proSchool.seguranca.dominio.Usuario;
import com.mossini.proSchool.seguranca.dominio.UsuarioRepositorio;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario createUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepo.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    public void deleteUsuario(Long usuarioId) {
        usuarioRepo.deleteById(usuarioId);
    }

    public Usuario getUsuarioById(Long usuarioId) {
        return usuarioRepo.findById(usuarioId).orElse(null);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepo.findAll();
    }
}
