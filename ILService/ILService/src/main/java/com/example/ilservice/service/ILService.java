package com.example.ilservice.service;

import com.example.ilservice.exception.IlAllReadyExistException;
import com.example.ilservice.exception.IlNotFoundException;
import com.example.ilservice.model.Il;
import com.example.ilservice.repository.ILRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ILService {

    private final ILRepository ilRepository;

    public List<Il> getIller(String name) {

        if (name == null) {
            return ilRepository.findAll();
        } else {
            return ilRepository.findAllByName(name);
        }

    }

    public Il createIl(Il newIl) {

        Optional<Il> ilByName = ilRepository.findByName(newIl.getName());
        if (ilByName.isPresent()) {
            throw new IlAllReadyExistException("Il allready exist with name " + newIl);
        }

        Optional<Il> ilByPlaka = ilRepository.findByPlaka(newIl.getPlaka());
        if (ilByPlaka.isPresent()) {
            throw new IlAllReadyExistException("Il allready exist with plaka " + newIl);
        }




        return ilRepository.save(newIl);
    }

    public void deleteIl(String id) {
        ilRepository.deleteById(id);
    }

    public void updateIl(String id, @NotNull Il newIl) {

        Il oldIl = getIlById(id);
        oldIl.setName(newIl.getName());
        oldIl.setPlaka(newIl.getPlaka());
        ilRepository.save(oldIl);

    }

    public Il getIlById(String id) {
        return ilRepository.findById(id)
                .orElseThrow(() -> new IlNotFoundException("Il not found with id" + id));


    }


}
