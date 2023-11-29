import random
import os

q = lambda z: ''.join([v, v + ''.join(random.choice(list(map(chr, range(768, 815)))) for i in range(int(random.normalvariate(10, 5))))][v.isalpha()] for v in z)

messages = ["change your stars",
            "what did you want to be raymond k hessel ?",
            "this is your pain ",
            "i see you",
            "commit queen in da house"]

for n in range(random.randint(1, 10)):
    message = q(random.choice(messages))

    # Generar un número aleatorio para el nombre del archivo txt
    file_number = random.randint(1, 10)

    # Crear el directorio `dumpCommit` si no existe
    if not os.path.exists("dumpCommit"):
        os.mkdir("dumpCommit")

    # Crear el archivo txt con el nombre `generated-[numero random de archivo txt]`
    os.system(f"echo '{message}' > dumpCommit/generated-{file_number}.txt")
