package com.akapush.rubbish.service;

import com.akapush.rubbish.domain.bean.RubbishGroupStats;
import com.akapush.rubbish.domain.model.Rubbish;
import com.akapush.rubbish.domain.model.RubbishType;

public interface RubbishService {

	public Iterable<Rubbish> getRubbishes(Long userId);

	public Rubbish addRubbish(Rubbish rubbish);

	public void deleteRubbish(Long rubbishId);

	public RubbishGroupStats getRubbishGroupStats(Long groupId);

	public Iterable<RubbishType> getRubbishesTypes();

	public RubbishType getRubbishType(Long rubbishTypeId);

	public Iterable<Rubbish> getSmoothedRubbishes(Long valueOf);

	public Iterable<Rubbish> smoothRubbishesList(Iterable<Rubbish> rubbishes);

}
