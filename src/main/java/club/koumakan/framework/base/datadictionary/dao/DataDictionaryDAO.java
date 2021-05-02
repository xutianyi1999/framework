package club.koumakan.framework.base.datadictionary.dao;

import club.koumakan.framework.base.datadictionary.entity.DataDictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataDictionaryDAO extends JpaRepository<DataDictionary, Long> {
}
