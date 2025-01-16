package org.example.onlinecourse.service;

import lombok.RequiredArgsConstructor;
import org.example.onlinecourse.dto.teacher.TeacherRequestDto;
import org.example.onlinecourse.dto.teacher.TeacherResponseDto;
import org.example.onlinecourse.entities.Teacher;
import org.example.onlinecourse.exceptions.DataAlreadyExists;
import org.example.onlinecourse.exceptions.DataNotFoundException;
import org.example.onlinecourse.mapper.TeacherMapper;
import org.example.onlinecourse.repository.TeacherRepository;
import org.example.onlinecourse.utils.Messages;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public ResponseEntity<TeacherResponseDto> addTeacher(TeacherRequestDto requestDto) {
        if (teacherRepository.existsByPhoneNumber(requestDto.getPhoneNumber())) {
            throw new DataAlreadyExists(Messages.UNIVERSAL_ALREADY_EXISTS.formatted("TEACHER,PHONE NUMBER"));
        }
    return   ResponseEntity.ok(teacherMapper.toDto(
            (teacherRepository.save(teacherMapper.toTeacher(requestDto)))));
    }

    @Override
    public ResponseEntity<TeacherResponseDto> getById(UUID id) {
        return ResponseEntity.ok(teacherMapper.toDto( teacherRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException
                        (Messages.NOT_FOUND.formatted("TEACHER", "BY THIS ID" + id)))));

    }

    @Override
    public ResponseEntity<List<TeacherResponseDto>> getAll() {
        return ResponseEntity.ok(
                teacherRepository.findAll()
                        .stream()
                        .map(teacherMapper::toDto)
                        .toList()
        );
    }

    @Override
    public ResponseEntity<TeacherResponseDto> updateTeacher(UUID id, TeacherRequestDto requestDto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException
                        (Messages.NOT_FOUND.formatted("TEACHER", "BY THIS ID" + id)));
        if(!teacher.getPhoneNumber().equals(requestDto.getPhoneNumber())){
            if (teacherRepository.existsByPhoneNumber(requestDto.getPhoneNumber())) {
                throw new DataAlreadyExists(Messages.UNIVERSAL_ALREADY_EXISTS.formatted("TEACHER,PHONE NUMBER"));
            }
        }
        teacher.setFirstName(requestDto.getFirstName());
        teacher.setLastName(requestDto.getLastName());
        teacher.setPhoneNumber(requestDto.getPhoneNumber());
      return ResponseEntity.ok(teacherMapper.toDto(teacherRepository.save(teacher)));

    }
}
