package com.akapush.rubbish.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akapush.rubbish.domain.bean.RubbishGroupStats;
import com.akapush.rubbish.domain.dto.RubbishDTO;
import com.akapush.rubbish.domain.model.Rubbish;
import com.akapush.rubbish.domain.model.RubbishType;
import com.akapush.rubbish.domain.model.User;
import com.akapush.rubbish.service.RubbishService;
import com.akapush.rubbish.service.UserService;
import com.akapush.rubbish.utils.DTOHelper;

@RestController
public class RubbishController {

	@Autowired
	private RubbishService rubbishService;

	@Autowired
	private UserService userService;

	@Autowired
	private DTOHelper dtoHelper;

	@RequestMapping(value = "/api/rubbish", method = RequestMethod.POST)
	public ResponseEntity<RubbishDTO> addRubbish(@AuthenticationPrincipal String userId,
			@RequestBody RubbishDTO rubbishDTO) {
		User user = userService.getUserById(Long.valueOf(userId));
		Rubbish rubbish = dtoHelper.getRubbish(user, rubbishDTO);
		Long rubbishTypeId = (long) rubbishDTO.getType();
		RubbishType rubbishType = rubbishService.getRubbishType(rubbishTypeId);
		rubbish.setRubbishType(rubbishType);
		Rubbish result = rubbishService.addRubbish(rubbish);
		RubbishDTO resultDTO = dtoHelper.getRubbishDTO(result);
		return new ResponseEntity<RubbishDTO>(resultDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/rubbishes", method = RequestMethod.GET)
	public ResponseEntity<Iterable<RubbishDTO>> getRubbishes(@AuthenticationPrincipal String userId,
			@RequestParam String type) {
		Iterable<Rubbish> rubbishes = null;
		if ("list".equals(type)) {
			rubbishes = rubbishService.getRubbishes(Long.valueOf(userId));
		} else if ("smoothed".equals(type)) {
			// TODO convertir smoothed en enum
			rubbishes = rubbishService.getSmoothedRubbishes(Long.valueOf(userId));
		}
		Iterable<RubbishDTO> rubbishedDTOs = dtoHelper.getRubbishesDTO(rubbishes);
		return new ResponseEntity<Iterable<RubbishDTO>>(rubbishedDTOs, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/rubbish/{rubbishId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteRubbish(@PathVariable("rubbishId") Long rubbishId) {
		rubbishService.deleteRubbish(rubbishId);
		return new ResponseEntity<String>("{}", HttpStatus.OK);

	}

	@RequestMapping(value = "/api/stats/group/{groupId}", method = RequestMethod.GET)
	public ResponseEntity<RubbishGroupStats> getRubbishGroupStats(@PathVariable("groupId") Long groupId) {
		RubbishGroupStats rubbishGroupStats = rubbishService.getRubbishGroupStats(groupId);
		return new ResponseEntity<RubbishGroupStats>(rubbishGroupStats, HttpStatus.OK);

	}

	@RequestMapping(value = "/api/rubbishtypes", method = RequestMethod.GET)
	public ResponseEntity<Iterable<RubbishType>> getRubbisheTypes() {
		Iterable<RubbishType> rubbishTypes = rubbishService.getRubbishesTypes();
		return new ResponseEntity<Iterable<RubbishType>>(rubbishTypes, HttpStatus.OK);
	}
}