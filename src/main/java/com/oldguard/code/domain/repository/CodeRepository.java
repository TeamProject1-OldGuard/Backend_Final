package com.oldguard.code.domain.repository;

import com.oldguard.code.domain.Code;
import com.oldguard.database.BPlusTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Hashtable;
import java.util.Optional;

@Slf4j
@Repository
public class CodeRepository {
    //BPlusTree<Code> codes=new BPlusTree<>();
    Hashtable<Long,Code> codes =new Hashtable();
    private static Long id=0L;

    public void save(Code code){
        codes.put(id++, code);
    }
    public Optional<Code> findByCode(String code){
        return codes.values().stream()
                .filter(findCode -> findCode.getCode().equals(code)).findAny();
    }
}
