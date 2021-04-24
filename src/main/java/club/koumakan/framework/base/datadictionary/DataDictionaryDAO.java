package club.koumakan.framework.base.datadictionary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataDictionaryDAO extends JpaRepository<DataDictionary, Long> {
}
