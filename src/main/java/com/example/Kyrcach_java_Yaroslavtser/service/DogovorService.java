package com.example.Kyrcach_java_Yaroslavtser.service;

import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.DogovorDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.WorkDTO;

public interface DogovorService {


    DogovorDTO findByUserId(Long id);

    void update(DogovorDTO dogovor);
}
