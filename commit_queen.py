import random
import os

q = lambda z: ''.join([v, v + ''.join(random.choice(list(map(chr, range(768, 815)))) for i in range(int(random.normalvariate(10, 5))))][v.isalpha()] for v in z)

messages = ["update readme.md", "update", "initial commit"]

for n in range(random.randint(1, 6)):
    to_write = f'{random.choice(messages)}\n' * 100

    if n > 4:
        # Check if the file exists
        if not os.path.exists('bin/generated-{random.randint(1, 10)}.txt'):
            # Create the file
            os.system(f'echo "{to_write}" > "bin/generated-{random.randint(1, 10)}.txt"')

        # Add the file to the staging area
        os.system(f'git add "bin/generated-{random.randint(1, 10)}.txt",)
    else:
        # Append to the file
        os.system(f'echo "{to_write}" >> bin/generated-{random.randint(1, 10)}.txt')

    os.system('git commit -m "{q(random.choice(messages))}"')
