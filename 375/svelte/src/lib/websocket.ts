import ReconnectingWebsocket from "reconnecting-websocket";
import { grid } from "./stores";

const url = "ws://localhost:5173/app";

export let socket = new ReconnectingWebsocket(url);

socket.onmessage = (msg) => {
  const dataString: string = msg.data;
  const data = JSON.parse(dataString);
  console.log(data);

  if (data.grid) {
    grid.set(data.grid);
  }
};

export const sendBuildMessage = (size: number, types: number) => {
  const data = {
    message: "make_grid",
    data: {
      size,
      types,
    },
  };

  socket.send(JSON.stringify(data));
};

export const sendStartMessage = (threads: number) => {
  const data = {
    message: "go!",
    data: {
      threads,
    },
  };

  socket.send(JSON.stringify(data));
};

export const sendStopMessage = () => {
  const data = {
    message: "stop!",
  };

  socket.send(JSON.stringify(data));
};
