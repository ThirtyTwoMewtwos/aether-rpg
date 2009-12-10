package com.aether.model.items;

/*
 * CharacterBackpack.java
 *
 * Copyright (c) 2008, Tyler Hoersch
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the University of Wisconsin Oshkosh nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY COPYRIGHT HOLDERS AND CONTRIBUTORS ''AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aether.model.Item;

public class CharacterBackpack implements EquipmentContainer {
	private int weightCarried = 0;
	private Map<String, Item> items = new HashMap<String, Item>();
	
	@Override
	public int weightCarried() {
		return weightCarried;
	}

	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}

	@Override
	public void addItem(Item toAdd) {
		if (!items.containsKey(toAdd.getName())) {
			weightCarried += toAdd.getWeight();
			items.put(toAdd.getName(), toAdd);
		}
	}

	public void removeItem(Item toRemove) {
		if (items.containsKey(toRemove.getName())) {
			weightCarried -= toRemove.getWeight();
			items.remove(toRemove.getName());
		}
	}

	public int itemCount() {
		return items.size();
	}

	@Override
	public Collection<String> getAllItemNames() {
		return items.keySet();
	}
}
