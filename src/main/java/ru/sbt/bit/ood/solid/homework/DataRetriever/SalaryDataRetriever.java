package ru.sbt.bit.ood.solid.homework.DataRetriever;

import ru.sbt.bit.ood.solid.homework.Containers.RequestParameters;
import ru.sbt.bit.ood.solid.homework.Containers.SalaryInfoEntry;

import java.util.Collection;

/**
 * Created by artem on 23.03.15.
 */
public interface SalaryDataRetriever {
    Collection<SalaryInfoEntry> getSalaryData(RequestParameters requestParameters);
}
