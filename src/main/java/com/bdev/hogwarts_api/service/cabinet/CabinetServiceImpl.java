package com.bdev.hogwarts_api.service.cabinet;

import com.bdev.hogwarts_api.dao.CabinetDao;
import com.bdev.hogwarts_api.data.converter.cabinet.CabinetDtoConverter;
import com.bdev.hogwarts_api.data.converter.cabinet.CabinetModelConverter;
import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class CabinetServiceImpl implements CabinetService {
    @Autowired
    private CabinetDao cabinetDao;

    @Override
    public boolean exists(long id) {
        return cabinetDao.exists(id);
    }

    @Override
    public List<Cabinet> getAllCabinets() {
        return cabinetDao.findAll().stream().map(CabinetModelConverter::convert).collect(toList());
    }

    @Override
    public Optional<Cabinet> getCabinet(long id) {
        return Optional.ofNullable(cabinetDao.getOne(id)).map(CabinetModelConverter::convert);
    }

    @Override
    public long saveCabinet(Cabinet cabinet) {
        if (cabinet.getId() != null) {
            throw new RuntimeException("Cabinet id should be null during creation");
        }

        return cabinetDao.save(CabinetDtoConverter.convert(cabinet)).getId();
    }

    @Override
    public void editCabinet(Cabinet cabinet) {
        if (!cabinetDao.exists(cabinet.getId())) {
            throw new RuntimeException(format("Cabinet with id '%d' does not exist", cabinet.getId()));
        }

        cabinetDao.save(CabinetDtoConverter.convert(cabinet));
    }

    @Override
    public void deleteCabinet(long id) {
        if (!cabinetDao.exists(id)) {
            throw new RuntimeException(format("Cabinet with id '%d' does not exist", id));
        }

        cabinetDao.delete(id);
    }
}
