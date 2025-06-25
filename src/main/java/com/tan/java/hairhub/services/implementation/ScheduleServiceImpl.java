package com.tan.java.hairhub.services.implementation;

import com.tan.java.hairhub.dto.request.CreateScheduleDTO;
import com.tan.java.hairhub.dto.request.UpdateScheduleDTO;
import com.tan.java.hairhub.dto.response.ScheduleResponse;
import com.tan.java.hairhub.entities.Schedule;
import com.tan.java.hairhub.mapper.ScheduleMapper;
import com.tan.java.hairhub.repositories.ScheduleRepository;
import com.tan.java.hairhub.services.interfaces.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;
    private ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }


    @Override
    public List<ScheduleResponse> getAllSchedule(int pageIndex, int pageSize) {
        List<Schedule> listSchedule = this.scheduleRepository.getAllSchedule((pageIndex - 1) * pageSize, pageSize);
        List<ScheduleResponse> listScheduleResponse = new ArrayList<>();
        if(!listSchedule.isEmpty()) {
            listSchedule.forEach(schedule -> {
                ScheduleResponse scheduleResponse = this.scheduleMapper.toScheduleResponse(schedule);
                listScheduleResponse.add(scheduleResponse);
            });
        }
        return listScheduleResponse;
    }

    @Override
    public ScheduleResponse getScheduleById(int scheduleId) {
        Optional<Schedule> schedule = this.scheduleRepository.findById(scheduleId);
        if(schedule.isPresent()) {
            ScheduleResponse scheduleResponse = this.scheduleMapper.toScheduleResponse(schedule.get());
            return scheduleResponse;
        }
        return null;
    }

    @Override
    public ScheduleResponse createSchedule(CreateScheduleDTO createScheduleDTO) {
        Schedule createSchedule = this.scheduleMapper.createSchedule(createScheduleDTO);
        if(createSchedule != null) {
            Schedule result = this.scheduleRepository.save(createSchedule);
            ScheduleResponse scheduleResponse = this.scheduleMapper.toScheduleResponse(result);
            return  scheduleResponse;
        }
        return null;
    }

    @Override
    public Schedule updateSchedule(UpdateScheduleDTO updateScheduleDTO) throws Exception {
        Optional<Schedule> schedule = this.scheduleRepository.findById(updateScheduleDTO.getScheduleId());
        if(!schedule.isPresent()) {
           throw new Exception("Schedule does not exist");
        }
        Schedule updateSchedule = this.scheduleMapper.updateSchedule(updateScheduleDTO, schedule.get());
        this.scheduleRepository.save(updateSchedule);
        return updateSchedule;
    }

    @Override
    public void deleteSchedule(int scheduleId) throws Exception {
        Optional<Schedule> schedule = this.scheduleRepository.findById(scheduleId);
        if(!schedule.isPresent()) {
            throw new Exception("Schedule does not exist");
        }
        this.scheduleRepository.delete(schedule.get());
    }
}
