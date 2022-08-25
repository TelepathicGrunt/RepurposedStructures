import argparse
from typing import Sequence

from mcresources import ResourceManager, utils
from mcresources.type_definitions import Json

import structures

FORGE_PATH = '../datapacks/forge/src/main/resources'
QUILT_PATH = '../datapacks/quilt/src/main/resources'

def generate_at_path(path: str):
    parser = argparse.ArgumentParser(description='Generate resources for Firmalife')
    rm = ResourceManager('repurposed_structures', resource_dir=path)
    parser.add_argument('--clean', action='store_true', dest='clean', help='Clean all auto generated resources')
    args = parser.parse_args()

    if args.clean:
        for tries in range(1, 1 + 3):
            try:
                utils.clean_generated_resources('/'.join(rm.resource_dir))
                print('Clean Success')
                return
            except:
                print('Failed, retrying (%d / 3)' % tries)
        print('Clean Aborted')
        return

    generate_all(rm)
    rm.flush()
    print('New = %d, Modified = %d, Unchanged = %d, Errors = %d' % (rm.new_files, rm.modified_files, rm.unchanged_files, rm.error_files))


def generate_all(rm: ResourceManager):
    structures.generate(rm)

if __name__ == '__main__':
    generate_at_path(FORGE_PATH)
    generate_at_path(QUILT_PATH)
