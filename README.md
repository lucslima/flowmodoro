# Flowmodoro

Timer de foco no estilo Pomodoro, pensado para quem tem TDAH: em vez de parar a cada 25 minutos, você estuda **até cansar** e ganha uma pausa proporcional ao tempo focado.

Interface retrô de monitor CRT (fósforo verde, âmbar ou ciano).

## Como usar

Abra o `index.html` no navegador (não precisa instalar nada).

1. **INICIAR**: o cronômetro conta para cima enquanto você estuda.
2. **CANSEI, PAUSA**: quando sentir que cansou, aperte. A pausa é calculada pelo tempo focado.
3. A pausa conta para baixo e toca um alarme 8-bit no final.

## Proporção foco/pausa

| Proporção | 25 min | 60 min | 90 min |
|-----------|--------|--------|--------|
| **5:1** (padrão, a clássica do Flowmodoro) | 5 min | 12 min | 18 min |
| 4:1 | ~6 min | 15 min | ~22 min |
| 3:1 | ~8 min | 20 min | 30 min |

## Recursos

- Botão **PAUSAR** para interrupções (o tempo parado não conta)
- **Lembrete de hiperfoco** opcional (60/90/120 min) para checar se você ainda está rendendo
- Pausa mínima opcional (3 ou 5 min)
- Notificações do navegador, alarme sonoro e tempo no título da aba
- Estatísticas e registro do dia (salvos no navegador)
- Atalhos: `Espaço` iniciar/parar, `P` pausar, `S` pular pausa

## App Android

A pasta `android/` tem um app que mostra o mesmo `index.html` em tela cheia. No celular, o app também:

- toca um alarme e mostra uma notificação quando a pausa acaba, mesmo com a tela apagada;
- mantém a tela ligada enquanto o timer está rodando.

Para gerar o APK (precisa do Android SDK e do Java 17 ou mais novo):

```bash
cd android
gradle assembleRelease
```

O APK fica em `android/app/build/outputs/apk/release/app-release.apk`.
