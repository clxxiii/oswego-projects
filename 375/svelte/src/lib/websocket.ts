import ReconnectingWebsocket from "reconnecting-websocket"

const url = 'ws://localhost:5173/app'

export let socket = new ReconnectingWebsocket(url)


export const sendStartMessage = (stations: number, slots: number) => {
  const data = {
    stations,
    slots,
    threads: 4
  }

  socket.send(JSON.stringify(data))
}