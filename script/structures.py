from distutils.spawn import spawn
from typing import Dict, Any, Tuple

from mcresources import ResourceManager, utils
from mcresources.type_definitions import JsonObject
from mcresources.advancements import *

GENERIC_JIGSAW = 'repurposed_structures:generic_jigsaw_structure'
ANCIENT_CITY_VARIANTS = ('nether', 'end', 'ocean')

def generate(rm: ResourceManager):
    category = AdvancementCategory(rm, 'structure_advancements', 'minecraft:textures/block/dirt.png')

    for variant in ANCIENT_CITY_VARIANTS:
        configured_structure(rm, 'ancient_city', GENERIC_JIGSAW, variant, 7, {
            'min_y_allowed': 45,
            'terrain_height_radius_check': 3,
            'burying_type': 'AVERAGE_LAND',
            'start_height': {
                'absolute': -3
            },
            'spawn_overrides': {'monster': spawn_override(spawner('minecraft:phantom', 3), spawner('minecraft:endermite', 2, 3, 7))}
        }
        )
        full_name = pluralize('repurposed_structures:ancient_city') + '_' + variant
        structure_set(rm, full_name, 112448534, 130, 65, ('repurposed_structures:ancient_city_%s' % variant, 1))
    advancement(rm, category, 'ancient_cities', 'minecraft:reinforced_deepslate', 'Civilization from the Beyond', 'Find all new Ancient Cities', *['repurposed_structures:ancient_city_%s' % v for v in ANCIENT_CITY_VARIANTS])


def pluralize(structure_type: str) -> str:
    return structure_type if structure_type.endswith('s') else ((structure_type[:-1] + 'ies') if structure_type.endswith('y') else (structure_type + 's'));

def configured_structure(rm: ResourceManager, base_name: str, structure_type: str, variant: str, size: int, config: Dict[str, Any], no_liquid: bool = True, terrain_adapt: str = 'beard_box', step: str = 'surface_structures', heightmap: str = None, valid_biome_radius: int = None):
    structure_type_plural = pluralize(base_name)
    name = f'{base_name}_{variant}'
    rm.write((*rm.resource_dir, 'data', rm.domain, 'worldgen', 'structure', name), {
        'type': structure_type,
        'start_pool': '%s:%s/%s/start_pool' % (rm.domain, structure_type_plural, variant),
        'size': size,
        'biomes': '#%s:has_structure:/%s/%s' % (rm.domain, structure_type_plural, variant),
        'cannot_spawn_in_liquid': no_liquid,
        'terrain_adaptation': terrain_adapt,
        'step': step,
        'project_start_to_heightmap': heightmap,
        'valid_biome_radius_check': valid_biome_radius,
        **config
    })

    rm.tag(structure_type_plural, 'worldgen/structure/collections', '%s:%s' % (rm.domain, name))
    rm.tag('%s/%s' % (structure_type_plural, variant), 'worldgen/biome/has_structure', '%s:%s' % (rm.domain, variant))

def spawn_override(*spawns: Dict[str, Any], bounding_box: str = 'full') -> JsonObject:
    return {
        'bounding_box': bounding_box,
        'spawns': [s for s in spawns]
    }

def spawner(entity_type: str, weight: int = 1, min_count: int = 1, max_count: int = 1) -> JsonObject:
    return {
        'type': entity_type,
        'weight': weight if weight != 1 else None,
        'min_count': min_count,
        'max_count': max_count
    }

def structure_set(rm: ResourceManager, name: str, salt: int, spacing: int, separation: int, *structures: Tuple[str, int], placement_type: str = 'repurposed_structures:advanced_random_spread'):
    res = utils.resource_location(rm.domain, name)
    rm.write((*rm.resource_dir, 'data', res.domain, 'worldgen', 'structure_set', res.path), { 
        'structures': [
            {
                'structure': s,
                'weight': w
            }
            for s, w, in structures
        ],
        'placement': {
            'type': placement_type,
            'salt': salt,
            'spacing': spacing,
            'separation': separation
        }
    })

def advancement(rm: ResourceManager, category: AdvancementCategory, name: str, icon: str, title: str, description: str, *structures: str, exp: int = 1000):
    category.advancement(name, icon, title, description, 'root', criteria=dict({structure: structure_trigger(structure) for structure in structures}), requirements=[[s] for s in structures])


def structure_trigger(structure: str) -> JsonObject:
    return {
        'trigger': 'minecraft:location',
        'conditions': {
            'player': [
                {
                    'condition': 'minecraft:entity_properties',
                    'entity': 'this',
                    'predicate': {
                        'location': {
                            'structure': structure
                        }
                    }
                }
            ]
        }
    }