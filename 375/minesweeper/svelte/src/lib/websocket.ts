import ReconnectingWebsocket from "reconnecting-websocket";
import { grid } from "./stores";

const url = "ws://localhost:8080/app";

export let socket = new ReconnectingWebsocket(url);

socket.onmessage = (msg) => {
  const dataString: string = msg.data;
  const data = JSON.parse(dataString);
  console.log(data);

  if (data.grid) {
    grid.set(data.grid);
  }
};

export const sendBuildMessage = (
  width: number,
  height: number,
  bombs: number
) => {
  const data = {
    action: "makeGrid",
    width,
    height,
    bombs,
  };

  console.log(data);
  socket.send(JSON.stringify(data));
};

export const sendFlagMessage = (x: number, y: number) => {
  const data = {
    action: "flag",
    x,
    y,
  };

  console.log(data);
  socket.send(JSON.stringify(data));
};

export const sendRevealMessage = (x: number, y: number) => {
  const data = {
    action: "reveal",
    x,
    y,
  };

  console.log(data);
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
